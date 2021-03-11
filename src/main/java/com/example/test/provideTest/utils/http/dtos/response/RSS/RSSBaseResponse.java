package com.example.test.provideTest.utils.http.dtos.response.RSS;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * RSS Base Response
 *
 * Object to parse the XML reponse of the RSS news API.
 * @author Manuel Pimentel
 */
@XmlRootElement(name = "rss")
public class RSSBaseResponse {

    @XmlElement(name = "channel")
    @Getter private List<RSSChannelElement> channelList;

    public RSSBaseResponse() {
    }
}
