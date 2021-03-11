package com.example.test.provideTest.exceptions.errorDictionaries;

import lombok.Getter;

/**
 * Object Parser Error Dictionary
 *
 * Enum to expose the valid error messages for ObjectParserException
 * @author Manuel Pimentel
 */
public enum ObjectParserErrorDictionary {
    XML_PARSING_ERROR("XML_PARSING_ERROR", "Could not parse the XML payload to class"),
    DATE_PARSING_ERROR("DATE_PARSING_ERROR", "Could not parse the date"),
    GRAPHQL_SCHEMA_PARSING_ERROR("GRAPHQL_SCHEMA_PARSING_ERROR", "Could not parse the GraphQL Schema"),
    INVALID_SCHEMA_PARSING_ERROR("INVALID_SCHEMA_PARSING_ERROR", "The provided schema is invalid"),
    MISSING_ARGUMENT_PARSING_ERROR("MISSING_ARGUMENT_PARSING_ERROR", "There is an argument missing to parse the GraphQL Schema");

    @Getter
    private String errorName;
    @Getter
    private String errorMessage;

    ObjectParserErrorDictionary(String errorName, String errorMessage) {
        this.errorName = errorName;
        this.errorMessage = errorMessage;
    }
}
