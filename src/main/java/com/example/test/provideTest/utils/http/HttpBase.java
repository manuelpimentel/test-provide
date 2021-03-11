package com.example.test.provideTest.utils.http;

import com.example.test.provideTest.exceptions.HttpConnectionException;
import com.example.test.provideTest.exceptions.errorDictionaries.ConnectionErrorDictionary;
import lombok.Data;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

/**
 * Http Base
 *
 * Base class to handle all the HTTP request generated from the application.
 * @author Manuel Pimentel
 */
@Data @Log
public class HttpBase {
    protected HttpURLConnection conn;
    protected String baseURL;
    protected String path;
    protected String method;
    protected Map<String, String> headers;

    HttpBase(String baseURL, String path, String method, Map<String, String> headers) {
        this.baseURL = baseURL;
        this.path = path;
        this.method = method;
        this.headers = headers;
    }

    /**
     * Configure Connection
     *
     * Method to configure the HTTP request to be executed. In this method is configured the base URL, path, headers,
     * query params, connection timeou, etc. Of the request.
     * @throws HttpConnectionException Thronw if any error happens in the configuration of the request
     */
    void configureConnection() throws HttpConnectionException {
        try {
            log.info("Creating " + this.method + " HTTP connection");
            final String fullUrl = baseURL + path;
            final URL url = new URL(fullUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(this.method);
            if (Objects.nonNull(headers)) {
                for (Map.Entry<String, String> h : headers.entrySet()) {
                    conn.setRequestProperty(h.getKey(), h.getValue());
                }
            }
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
        } catch (SocketTimeoutException se) {
            log.severe(ConnectionErrorDictionary.CONNECTION_TIMEOUT.getErrorMessage());
            throw new HttpConnectionException(ConnectionErrorDictionary.CONNECTION_TIMEOUT.getErrorMessage(), se);
        } catch (IOException e) {
            log.severe(ConnectionErrorDictionary.CONNECTION_CONFIGURATION_ERROR.getErrorMessage());
            throw new HttpConnectionException(ConnectionErrorDictionary.CONNECTION_CONFIGURATION_ERROR.getErrorMessage(), e);
        }
    }

    /**
     * Execute request
     *
     * This method handles the execution of the previously configured HTTP request.
     *
     * @return GenericHTTPResponse instance with the status and the payload of the response
     * @throws HttpConnectionException Thrown if any error happens in the HTTP request execution
     */
    GenericHTTPResponse executeRequest() throws HttpConnectionException {
        log.info("Executing " + this.method + " HTTP connection");
        try {
            final Integer status = conn.getResponseCode();

            connectionErrorMapping(status);

            final BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();
            return new GenericHTTPResponse(status, content.toString());
        } catch (IOException e) {
            log.severe(ConnectionErrorDictionary.CONNECTION_EXECUTION_ERROR.getErrorMessage());
            throw new HttpConnectionException(ConnectionErrorDictionary.CONNECTION_EXECUTION_ERROR.getErrorMessage(), e);
        }
    }

    /**
     * Connection Error Mapping
     *
     * This method handles the potential invalid responses from the HTTP request.
     *
     * @param status HTTP Status from the request.
     * @throws HttpConnectionException Thrown if the status is invalid.
     */
    private void connectionErrorMapping(Integer status) throws HttpConnectionException {
        switch (status) {
            case 500:
                log.severe(ConnectionErrorDictionary.UNEXPECTED_ERROR.getErrorMessage());
                throw new HttpConnectionException(ConnectionErrorDictionary.UNEXPECTED_ERROR.getErrorMessage());
            case 404:
                log.severe(ConnectionErrorDictionary.RESOURCE_NOT_FOUND.getErrorMessage());
                throw new HttpConnectionException(ConnectionErrorDictionary.RESOURCE_NOT_FOUND.getErrorMessage());
        }
    }

}
