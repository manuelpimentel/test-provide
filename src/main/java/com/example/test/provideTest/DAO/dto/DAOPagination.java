package com.example.test.provideTest.DAO.dto;

import com.example.test.provideTest.exceptions.errorDictionaries.ObjectParserErrorDictionary;
import com.example.test.provideTest.exceptions.graphQL.GraphQLObjectParsingException;
import lombok.Data;

import java.util.HashMap;
import java.util.Objects;

/**
 * DAO Pagination object.
 *
 * Class to support the pagination of the entities in the DAO command instances.
 *
 * @author Manuel Pimentel
 */
@Data public class DAOPagination {
    private String criteria;
    private Integer size;
    private Integer page;

    public DAOPagination(Integer size) {
        this.size = size;
    }

    public DAOPagination(HashMap filter) throws GraphQLObjectParsingException {
        this.criteria = (String) filter.get("criteria");
        this.size = (Integer) filter.get("size");
        this.page = (Integer) filter.get("page");
        isValidGraphQL();
    }

    /**
     * This method validates the DAO Pagination object attributes
     *
     * @throws GraphQLObjectParsingException Thrown if the DAO Pagination object has invalid fields
     */
    private void isValidGraphQL() throws GraphQLObjectParsingException {
        if (Objects.nonNull(this.page) && Objects.isNull(this.size))
            throw new GraphQLObjectParsingException(ObjectParserErrorDictionary.MISSING_ARGUMENT_PARSING_ERROR.getErrorMessage(), null, "size");
    }

}
