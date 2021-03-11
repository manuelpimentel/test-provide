package com.example.test.provideTest.exceptions;

public class DAOCommandException extends BaseException {

    protected String invalidField;

    public DAOCommandException(String message, Throwable cause, String invalidField) {
        super(message, cause);
        this.invalidField = invalidField;
    }

    public DAOCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOCommandException(String message) {
        super(message);
    }
}
