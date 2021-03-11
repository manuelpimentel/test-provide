package com.example.test.provideTest.utils.http.dtos.response.RSS;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * RSS Enclosure Element
 *
 * Object to parse the XML response of the RSS news API.
 * @author Manuel Pimentel
 */
public class RSSEnclosureElement {

    @XmlAttribute(name = "type")
    @Getter private String _imageType;
    @XmlAttribute(name = "url")
    @Getter private String _imageURL;

    public RSSEnclosureElement() {
    }
}
