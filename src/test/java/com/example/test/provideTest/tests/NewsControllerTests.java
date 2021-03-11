package com.example.test.provideTest.tests;

import com.example.test.provideTest.DAO.dto.DAOPagination;
import com.example.test.provideTest.domain.News;
import com.example.test.provideTest.exceptions.BaseException;
import com.example.test.provideTest.controllers.NewsController;
import com.example.test.provideTest.utils.http.dtos.response.RSS.RSSBaseResponse;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:/application.properties")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Log
public class NewsControllerTests {
    @Autowired
    private NewsController newsController;

    /**
     * mockNewsList
     * Mock News entity list
     */
    @Mock
    List<News> _mockNewsList;

    /**
     * Initial method to create mock entities and setup the tests
     */
    @Before
    public void init() {
        _mockNewsList = Stream.of(
                new News(1L, "Test title 1", "Test description 1", "http://test1.image", new Date(), new Date()),
                new News(2L, "Test title 2", "Test description 2", "http://test2.image", new Date(), new Date())
        ).collect(Collectors.toList());
    }

    /**
     * Persist News
     *
     * Method to test the persistNews functionality
     */
    @Test
    public void persistNews() {
        try {
            List<News> persistedNews = newsController.persistNews(_mockNewsList);
            for (int i = 0; i <= _mockNewsList.size() - 1; i++) {
                assertEquals(_mockNewsList.get(i), persistedNews.get(i));
            }
        } catch (BaseException e) {
            log.severe("Error in persistNews test: " + e.getMessage());
        }
    }

    /**
     * Get RSS List
     *
     * Method to test the RSS News API HTTP request execution
     */
    @Test
    public void getRSSList() {
        try {
            final RSSBaseResponse genericResponse = newsController.getRSSNews();
            assertNotNull(genericResponse);
            assertNotNull(genericResponse.getChannelList());
        } catch (BaseException e) {
            log.severe("Error in getRSS test: " + e.getMessage());
        }
    }

    /**
     * Get News List
     *
     * Method to tests the getAllNews (from DB) functionality
     */
    @Test
    public void getNewsList() {
        final List<News> newsList = newsController.getAllNews(null);
        assertNotNull(newsList);
        assertTrue(newsList.parallelStream().anyMatch(n -> Objects.equals(n.get_id(), _mockNewsList.get(0).get_id())));
    }

    /**
     * Get News List
     *
     * Method to tests the getAllNews (from DB) functionality using pagination
     */
    @Test
    public void getPaginatedNewsList() {
        final Integer size = 1;
        final List<News> newsList = newsController.getAllNews(new DAOPagination(size));
        assertNotNull(newsList);
        assertTrue( newsList.size() <= size );
    }

}
