package com.example.test.provideTest.utils.graphQL.dto;

import com.example.test.provideTest.domain.News;
import lombok.Data;

import java.util.Date;

/**
 * News DTO
 *
 * DTO class to parse the response through GraphQL API.
 * @author Manuel Pimentel
 */
@Data
public class NewsDTO {

    private Long id;
    private String title;
    private String description;
    private String image;
    private Date date;
    private Date updatedDate;

    public NewsDTO(News news) {
        this.id = news.get_id();
        this.title = news.get_title();
        this.description = news.get_description();
        this.image = news.get_imageURL();
        this.date = news.get_date();
        this.updatedDate = news.get_updatedDate();
    }


}
