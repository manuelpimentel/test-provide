package com.example.test.provideTest.utils.graphQL.fetchers;

import com.example.test.provideTest.DAO.dto.DAOPagination;
import com.example.test.provideTest.controllers.NewsController;
import com.example.test.provideTest.domain.News;
import com.example.test.provideTest.exceptions.DAOCommandException;
import com.example.test.provideTest.exceptions.errorDictionaries.DAOCommandErrorDictionary;
import com.example.test.provideTest.exceptions.graphQL.GraphQLPersistenceException;
import com.example.test.provideTest.utils.graphQL.dto.NewsDTO;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * News Data Fetcher
 *
 * Class to handle GraphQL API request of News entities
 * @author Manuel Pimentel
 */
@Component
public class NewsDataFetcher {

    @Autowired
    NewsController newsController;

    /**
     * Get News By Id Data Fetcher
     *
     * Data Fetcher method to handle GraphQL API request to retrieve News entity by id
     *
     * @return DataFetcher instance
     */
    public DataFetcher getNewsByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            final String newsId = dataFetchingEnvironment.getArgument("id");
            try {
                return new NewsDTO(newsController.getNewsById(Long.valueOf(newsId)));
            } catch (DAOCommandException e) {
                throw new GraphQLPersistenceException(DAOCommandErrorDictionary.NO_RESULT.getErrorMessage(), e, "id");
            }
        };
    }

    /**
     * Get All News Data Fetcher
     *
     * Data Fetcher method to handle GraphQL API request to retrieve all News entity
     *
     * @return DataFetcher instance
     */
    public DataFetcher getAllNewsDataFetcher() {
        return dataFetchingEnvironment -> {
            final HashMap<String, String> filter = dataFetchingEnvironment.getArgument("filter");
            final List<News> newsList = newsController.getAllNews(Objects.nonNull(filter) ? new DAOPagination(filter) : null);
            return newsList.stream().map(NewsDTO::new).collect(Collectors.toList());
        };
    }

}
