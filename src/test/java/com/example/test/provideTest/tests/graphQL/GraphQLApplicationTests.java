package com.example.test.provideTest.tests.graphQL;

import com.example.test.provideTest.controllers.NewsController;
import com.example.test.provideTest.domain.News;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * GraphQL Application Tests
 *
 * Class to handle GraphQL API unit and integration tests
 * @author Manuel Pimentel
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Log
public class GraphQLApplicationTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private NewsController newsController;

    @Mock
    List<News> mockNewsList;

    /**
     * Initial method to create mock entities and setup the tests
     */
    @Before
    public void init()
    {
        mockNewsList = Stream.of(
                new News(1L, "Test title 1", "Test description 1", "http://test1.image", new Date(), new Date() ),
                new News(2L, "Test title 2", "Test description 2", "http://test2.image", new Date(), new Date() )
        ).collect(Collectors.toList());
    }

    /**
     * List News
     *
     * Method to tests the allNews service
     */
    @Test
    public void listNews() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
                    .content("{\"query\":\"{ allNews { id title date } }\"}")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            log.severe("Error in listNews test: " + e.getCause());
        }
    }

    /**
     * List News
     *
     * Method to tests the getNewsById service
     */
    @Test
    public void getNewsById() {
        try {
            final String id = mockNewsList.get(0).get_id().toString();
            mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
                    .content("{\"query\":\"{ newsById(id: "+id+") { id title date } }\"}")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(id))
                    .andReturn();
        } catch (Exception e) {
            log.severe("Error in getNewsById test: " + e.getCause());
        }
    }

}
