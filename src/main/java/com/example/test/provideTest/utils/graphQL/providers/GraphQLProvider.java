package com.example.test.provideTest.utils.graphQL.providers;

import com.example.test.provideTest.exceptions.ObjectParserException;
import com.example.test.provideTest.exceptions.errorDictionaries.ObjectParserErrorDictionary;
import com.example.test.provideTest.utils.graphQL.fetchers.NewsDataFetcher;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * GraphQL Provider
 *
 * Class to build schemas for GraphQL implementation
 * @author Manuel Pimentel
 */
@Component
public class GraphQLProvider {

    @Autowired
    private NewsDataFetcher newsDataFetcher;

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    /**
     * Method to get the predefined schema, parse it to String as an SDL and build the schema.
     *
     * @throws ObjectParserException Thrown if any error occurs parsing the GraphQL schema
     */
    @PostConstruct
    public void init() throws ObjectParserException {
        final URL url = Resources.getResource("schema.graphqls");
        String sdl;
        try {
            sdl = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new ObjectParserException(ObjectParserErrorDictionary.GRAPHQL_SCHEMA_PARSING_ERROR.getErrorMessage(), e);
        }
        if (Objects.isNull(sdl))
            throw new ObjectParserException(ObjectParserErrorDictionary.INVALID_SCHEMA_PARSING_ERROR.getErrorMessage());
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    /**
     * Build Schema
     *
     * Method to build the schema from an SDL
     *
     * @param sdl SDL provided to build the schema
     * @return GraphQLSchema instance
     */
    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    /**
     * Build Wiring
     *
     * Method to create the available services to be consumed through the GraphQL API.
     *
     * @return RuntimeWiring instance
     */
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("newsById", newsDataFetcher.getNewsByIdDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("allNews", newsDataFetcher.getAllNewsDataFetcher()))
                .build();
    }
}
