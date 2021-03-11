package com.example.test.provideTest.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * Param
 *
 * Param entity
 *
 * @author Manuel Pimentel
 */
@Entity
@Table(name = "system_param")
@SequenceGenerator(name = "system_param_id_seq", sequenceName = "system_param_id_seq", allocationSize = 1)
@Data public class Param {
    /**
     * _id
     * Param entity auto-incremental unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system_param_id_seq")
    @Column(name = "id")
    private Long _id;
    /**
     * _paramKey
     * Param entity unique text identifier
     */
    @Column(name = "param_key", unique = true)
        private String _paramKey;
    /**
     * _paramKey
     * Param entity stored value
     */
    @Column(name = "value")
    private String _value;
    /**
     * _paramKey
     * Param entity value's data type
     */
    @Column(name = "data_type")
    private String _dataType;
    /**
     * _creationDate
     * Param entity creation date
     */
    @Column(name = "creation_date")
    private Date _creationDate;
    /**
     * _updatedDate
     * Param entity updated date
     */
    @Column(name = "updated_date")
    private Date _updatedDate = new Date();

    /**
     * Param Keys
     *
     * Enum containing the available param keys.
     */
    public enum ParamKeys
    {
        RSS_BASE_URL("RSS_BASE_URL"),
        RSS_PATH("RSS_PATH");

        ParamKeys(String value) {
            this.value = value;
        }

        @Getter private String value;
    }

}
