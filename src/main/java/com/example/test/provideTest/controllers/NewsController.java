package com.example.test.provideTest.controllers;

import com.example.test.provideTest.DAO.atomic.news.GetAllNews;
import com.example.test.provideTest.DAO.atomic.news.GetNewsById;
import com.example.test.provideTest.DAO.dto.DAOPagination;
import com.example.test.provideTest.domain.News;
import com.example.test.provideTest.domain.Param;
import com.example.test.provideTest.exceptions.DAOCommandException;
import com.example.test.provideTest.exceptions.HttpConnectionException;
import com.example.test.provideTest.exceptions.ObjectParserException;
import com.example.test.provideTest.DAO.atomic.news.PersistNewsList;
import com.example.test.provideTest.DAO.atomic.param.GetParamByParamKey;
import com.example.test.provideTest.utils.NewsUtils;
import com.example.test.provideTest.utils.http.GenericHTTPResponse;
import com.example.test.provideTest.utils.http.HttpGet;
import com.example.test.provideTest.utils.http.HttpUtils;
import com.example.test.provideTest.utils.http.dtos.response.RSS.RSSBaseResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * News Controller
 *
 * Class with functions that work with News entity
 *
 * @author Manuel Pimentel
 */
@Component
@Log
public class NewsController {

    @Autowired
    GetParamByParamKey getParamByParamKey;
    @Autowired
    PersistNewsList persistNewsList;
    @Autowired
    NewsUtils newsUtils;
    @Autowired
    GetNewsById getNewsById;
    @Autowired
    GetAllNews getAllNews;

    /**
     * Handle RSS News request
     *
     * This method work as a wrapper to get the News from RSS Api, parse the data to the news entity and finally save it.
     *
     * @throws HttpConnectionException Thrown if there is any HTTP connection error
     * @throws ObjectParserException Thrown if the object received from the request could not be parsed
     * @throws DAOCommandException Thrown if any exception occurred executing any DAO Command
     */
    public void handleRSSNewsRequest() throws HttpConnectionException, ObjectParserException, DAOCommandException {
        final RSSBaseResponse resp = getRSSNews();
        persistNews(parseNewsResponse(resp));
    }

    /**
     * Get RSS News
     *
     * This method is in charge of formatting and executing an HTTP request to get the RSS News from the API.
     * Here we get the params from the database using the GetParamByParamKey command to create the base url,
     * add the Query Params necessary for the request and then create the request using the HttpGet object methods.
     *
     * @return RSSBaseResponse object contains the RSS News items returned by the API
     * @throws HttpConnectionException Thrown if there is any HTTP connection error
     * @throws ObjectParserException Thrown if the object received from the request could not be parsed
     * @throws DAOCommandException Thrown if any exception occurred executing any DAO Command
     */
    public RSSBaseResponse getRSSNews() throws HttpConnectionException, ObjectParserException, DAOCommandException {
        getParamByParamKey.setAttributes(Param.ParamKeys.RSS_BASE_URL.getValue());
        Param defaultParam = getParamByParamKey.execute();
        final String baseURL = defaultParam.get_value();
        getParamByParamKey.setAttributes(Param.ParamKeys.RSS_PATH.getValue());
        defaultParam = getParamByParamKey.execute();
        final String path = defaultParam.get_value();

        final Map<String, String> queryParams = new HashMap<>();
        queryParams.put("format", "xml");
        final HttpGet get = new HttpGet(baseURL, path, null, queryParams);
        final GenericHTTPResponse genericHTTPResponse = get.doRequest();
        return HttpUtils.parseXMLResponse(genericHTTPResponse.getPayload(), RSSBaseResponse.class);
    }

    /**
     * Persist News
     *
     * This method is in charge of saving/updating a list of News entity
     *
     * @param newsList List of News enitty to be persisted
     * @return A list of the persisted News is returned.
     * @throws DAOCommandException Thrown if any exception occurred executing any DAO Command
     */
    public List<News> persistNews(List<News> newsList) throws DAOCommandException {
        persistNewsList.setAttributes(newsList);
        return persistNewsList.execute();
    }

    /**
     * Get News By Id
     *
     * Method to return a single News entity object by id
     *
     * @param id News object unique identifier
     * @return News entity instance
     * @throws DAOCommandException Thrown if any exception occurred executing any DAO Command
     */
    public News getNewsById(Long id) throws DAOCommandException {
        getNewsById.setAttributes(id);
        return getNewsById.execute();
    }

    /**
     * Get All News
     *
     * Method to return a list of News entity objects
     *
     * @param filter DAO Pagination class instance to filter
     * @return List of News entity objects
     */
    public List<News> getAllNews(DAOPagination filter)
    {
        getAllNews.setAttributes(filter);
        return getAllNews.execute();
    }

    /**
     * Parse News Response
     *
     * This method parse the RSSBaseResponse to a List of News entity
     *
     * @param baseNewsList Object of RSSBaseResponse class containing the items received from the RSS API request.
     * @return A list of the parsed News entity.
     */
    private List<News> parseNewsResponse(RSSBaseResponse baseNewsList) {
        List<News> newsList = new ArrayList<>();
        baseNewsList.getChannelList().forEach(
                c -> newsList.addAll(c.get_itemList().parallelStream()
                        .map(item -> {
                            final News news = new News(item);
                            try {
                                news.set_id(newsUtils.parseNewsId(item));
                                news.set_description(newsUtils.cleanHTMLContent(item.get_description()));
                                news.set_date(newsUtils.parseStringDateToDate(NewsUtils.RSS_DEFAULT_DATE_FORMAT, item.get_date()));
                            } catch (ObjectParserException e) {
                                log.severe(e.getMessage());
                            }
                            return news;
                        })
                        .collect(Collectors.toList())
                )
        );

        return newsList;
    }


}
