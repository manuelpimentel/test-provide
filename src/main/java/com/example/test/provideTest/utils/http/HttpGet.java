package com.example.test.provideTest.utils.http;

import com.example.test.provideTest.exceptions.HttpConnectionException;

import java.util.Iterator;
import java.util.Map;

public class HttpGet extends HttpBase {

    private Map<String, String> queryParams;

    public HttpGet(String baseURL, String path, Map<String, String> headers, Map<String, String> queryParams) {
        super(baseURL, path, "GET", headers);
        this.queryParams = queryParams;
        this.path = handleParams(path);
    }

    public GenericHTTPResponse doRequest() throws HttpConnectionException {
        configureConnection();
        return executeRequest();
    }

    private String handleParams(String path) {
        if(queryParams == null || queryParams.size() == 0)
            return path;
        path += "?";
        StringBuilder pathBuilder = new StringBuilder(path);
        for (String key : queryParams.keySet()) {
            pathBuilder.append(key).append("=").append(queryParams.get(key));
        }
        path = pathBuilder.toString();
        return path;
    }
}
