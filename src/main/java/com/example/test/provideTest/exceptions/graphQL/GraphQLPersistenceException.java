package com.example.test.provideTest.exceptions.graphQL;

import com.example.test.provideTest.exceptions.DAOCommandException;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * GraphQL Object Parsing Exception
 *
 * Extends from DAOCommandException and is in charge of handling the persistence layer exceptions
 * related to GraphQL implementations.
 *
 * @author Manuel Pimentel
 */
public class GraphQLPersistenceException extends DAOCommandException implements GraphQLError {

    public GraphQLPersistenceException(String message, Throwable cause, String invalidField) {
        super(message, cause, invalidField);
    }


    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("invalidField", this.invalidField);
    }
}
