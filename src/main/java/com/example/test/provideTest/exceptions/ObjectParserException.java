package com.example.test.provideTest.exceptions;

public class ObjectParserException extends BaseException {

    protected String invalidField;

    public ObjectParserException(String message, Throwable cause, String invalidField) {
        super(message, cause);
        this.invalidField = invalidField;
    }

    public ObjectParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectParserException(String message) {
        super(message);
    }
}
