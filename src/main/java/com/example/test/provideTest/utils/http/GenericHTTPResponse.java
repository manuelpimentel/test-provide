package com.example.test.provideTest.utils.http;


import lombok.Data;

/**
 * Generic HTTP Response
 *
 * Object to handle a generic http response of an API request.
 * @author Manuel Pimentel
 */
@Data public class GenericHTTPResponse {

    private Integer statusCode;
    private String payload;

    GenericHTTPResponse(Integer statusCode, String payload) {
        this.statusCode = statusCode;
        this.payload = payload;
    }
}
