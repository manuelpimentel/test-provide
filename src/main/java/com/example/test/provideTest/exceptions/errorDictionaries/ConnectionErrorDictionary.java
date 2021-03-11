package com.example.test.provideTest.exceptions.errorDictionaries;

import lombok.Getter;

/**
 * Connection Error Dictionary
 *
 * Enum to expose the valid error messages for ConnectionErrorException
 * @author Manuel Pimentel
 */
public enum ConnectionErrorDictionary {
    RESOURCE_NOT_FOUND("RESOURCE_NOR_FOUND", "The specified url or endpoint not found"),
    UNEXPECTED_ERROR("UNEXPECTED_ERROR", "An unexpected connection error occurred"),
    CONNECTION_EXECUTION_ERROR("CONNECTION_EXECUTION_ERROR", "An error occurred executing the HTTP connection"),
    CONNECTION_CONFIGURATION_ERROR("CONNECTION_CONFIGURATION_ERROR", "An error occurred configuring the HTTP connection"),
    CONNECTION_TIMEOUT("CONNECTION_TIMEOUT", "Connection timeout");

    @Getter
    private String errorName;
    @Getter
    private String errorMessage;

    ConnectionErrorDictionary(String errorName, String errorMessage) {
        this.errorName = errorName;
        this.errorMessage = errorMessage;
    }
}
