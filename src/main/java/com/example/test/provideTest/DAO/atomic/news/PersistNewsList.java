package com.example.test.provideTest.DAO.atomic.news;

import com.example.test.provideTest.DAO.config.DAOCommand;
import com.example.test.provideTest.domain.News;
import com.example.test.provideTest.exceptions.DAOCommandException;
import com.example.test.provideTest.exceptions.errorDictionaries.DAOCommandErrorDictionary;
import com.example.test.provideTest.repositories.NewsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Persist News List
 *
 * DAO Command implementation to persist a list of News entity instances.
 * @author Manuel Pimentel
 */
@Component
@Log
public class PersistNewsList extends DAOCommand<List<News>> {
    private List<News> _newsList;

    @Autowired
    NewsRepository newsRepository;

    @Override
    public List<News> execute() throws DAOCommandException {
        try {
            return newsRepository.saveAll(_newsList);
        } catch (Exception e) {
            throw new DAOCommandException(DAOCommandErrorDictionary.SAVING_UPDATING_ERROR.getErrorMessage(), e);
        }
    }

    @Override
    public void setAttributes(Object... args) throws DAOCommandException {
        try {
            _newsList = (List<News>) args[0];
        }catch (Exception e)
        {
            throw new DAOCommandException(DAOCommandErrorDictionary.INVALID_DATA.getErrorMessage(), e);
        }
    }
}
