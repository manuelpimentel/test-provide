package com.example.test.provideTest.utils.scheduler;

import com.example.test.provideTest.controllers.NewsController;
import com.example.test.provideTest.exceptions.DAOCommandException;
import com.example.test.provideTest.exceptions.HttpConnectionException;
import com.example.test.provideTest.exceptions.ObjectParserException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Schedulers Container
 *
 * Class that contains the system schedulers
 * @author Manuel Pimentel
 */
@Component
@Log
public class SchedulersContainer {

    @Autowired
    private NewsController newsController;

    /**
     * Get Feed Scheduler
     *
     * Scheduler to execute the request to the RSS news API to retrieve the news every 5 minutes.
     */
    @Scheduled(fixedDelay = 1000 * 60 * 5)
    private void getFeedScheduler() {
        try {
            newsController.handleRSSNewsRequest();
        } catch (HttpConnectionException | ObjectParserException | DAOCommandException e) {
            log.severe(e.getMessage());
        }
    }

}
