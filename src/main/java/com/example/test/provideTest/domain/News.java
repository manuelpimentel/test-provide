package com.example.test.provideTest.domain;

import com.example.test.provideTest.utils.http.dtos.response.RSS.RSSItemElement;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * News
 *
 * News entity
 *
 * @author Manuel Pimentel
 */
@Entity
@Table(name = "News")
@Data public class News {

    /**
     * _id
     * News entity unique identifier
     */
    @Id
    @Column(name = "id")
    private Long _id;
    /**
     * _title
     * News entity title
     */
    @Column(name = "title")
    private String _title;
    /**
     * _description
     * News entity description
     */
    @Column(name = "description", length = 25000)
    private String _description;
    /**
     * _imageURL
     * News entity image URL
     */
    @Column(name = "image_url", length = 25000)
    private String _imageURL;
    /**
     * _date
     * News entity publication date
     */
    @Column(name = "date")
    private Date _date;
    /**
     * _date
     * News entity updated date
     */
    @Column(name = "updated_date")
    private Date _updatedDate;

    public News() {
    }

    public News(Long _id, String _title, String _description, String _imageURL, Date _date, Date _updatedDate) {
        this._id = _id;
        this._title = _title;
        this._description = _description;
        this._imageURL = _imageURL;
        this._date = _date;
        this._updatedDate = _updatedDate;
    }

    public News(RSSItemElement item) {
        this._title = item.get_title();
        this._updatedDate = new Date();
        this._description = item.get_description();
        this._imageURL = item.get_enclosure().get_imageURL();
    }
}
