package com.example.test.provideTest.DAO.atomic.news;

import com.example.test.provideTest.DAO.config.DAOCommand;
import com.example.test.provideTest.domain.News;
import com.example.test.provideTest.exceptions.DAOCommandException;
import com.example.test.provideTest.exceptions.errorDictionaries.DAOCommandErrorDictionary;
import com.example.test.provideTest.repositories.NewsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;

/**
 * Get News By Id
 *
 * DAO Command implementation to get News entity instance by ID
 * @author Manuel Pimentel
 */
@Component
@Log
public class GetNewsById extends DAOCommand<News> {
    private Long _id;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News execute() throws DAOCommandException {
        try {
            final Optional<News> optionalNews = newsRepository.findNewsBy_id(_id);
            if (!optionalNews.isPresent())
                throw new DAOCommandException(DAOCommandErrorDictionary.NO_RESULT.getErrorMessage());
            return optionalNews.get();
        } catch (NonUniqueResultException e) {
            throw new DAOCommandException(DAOCommandErrorDictionary.MORE_THAN_ONE_RESULT.getErrorMessage(), e);
        }
    }

    @Override
    public void setAttributes(Object... args) throws DAOCommandException {
        try {
            _id = (Long) args[0];
        }catch (Exception e)
        {
            throw new DAOCommandException(DAOCommandErrorDictionary.INVALID_DATA.getErrorMessage(), e);
        }
    }
}
