package com.example.test.provideTest.utils.http.dtos.response.RSS;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * RSS Channel Element
 *
 * Object to parse the XML response of the RSS news API.
 * @author Manuel Pimentel
 */
public class RSSChannelElement {

    @XmlElement(name = "title")
    @Getter private String _title;
    @XmlElement(name = "item")
    @Getter private List<RSSItemElement> _itemList;

    public RSSChannelElement() {
    }

}
