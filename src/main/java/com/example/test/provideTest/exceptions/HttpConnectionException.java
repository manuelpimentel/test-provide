package com.example.test.provideTest.exceptions;

public class HttpConnectionException extends BaseException {

    public HttpConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpConnectionException(String message) {
        super(message);
    }
}
