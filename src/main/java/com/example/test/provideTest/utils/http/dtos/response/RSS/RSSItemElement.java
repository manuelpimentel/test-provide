package com.example.test.provideTest.utils.http.dtos.response.RSS;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

/**
 * RSS Item Element
 *
 * Object to parse the XML response of the RSS news API.
 * @author Manuel Pimentel
 */
public class RSSItemElement {
    @XmlElement(name = "title")
    @Getter private String _title;
    @XmlElement(name = "link")
    @Getter private String _link;
    @XmlElement(name = "description")
    @Getter private String _description;
    @XmlElement(name = "pubDate")
    @Getter private String _date;
    @XmlElement(name = "enclosure")
    @Getter private RSSEnclosureElement _enclosure;

    public RSSItemElement() {
    }
}
