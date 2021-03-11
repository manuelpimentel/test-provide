package com.example.test.provideTest.DAO.atomic.news;

import com.example.test.provideTest.DAO.config.DAOCommand;
import com.example.test.provideTest.DAO.dto.DAOPagination;
import com.example.test.provideTest.domain.News;
import com.example.test.provideTest.repositories.NewsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * GetAllNews DAO Command
 *
 * This command return a list of News entities
 * @author Manuel Pimentel
 */
@Component
@Log
public class GetAllNews extends DAOCommand<List<News>> {

    @Autowired
    NewsRepository newsRepository;

    @Override
    public List<News> execute() {
        final List<News> _newsList;
        if (Objects.nonNull(daoPagination) && Objects.nonNull(daoPagination.getCriteria())) {
            _newsList = newsRepository.findAllFiltering("%" + daoPagination.getCriteria() + "%");
        } else _newsList = newsRepository.findAll();
        return performPagination(_newsList);
    }

    @Override
    public void setAttributes(Object... args) {
        daoPagination = Objects.nonNull(args[0]) ? (DAOPagination) args[0] : null;
    }

}
