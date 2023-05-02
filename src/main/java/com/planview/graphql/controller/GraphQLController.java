package com.planview.graphql.controller;

import com.planview.graphql.service.GraphQLSchemaGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/api")
public class GraphQLController {

    @Autowired
    GraphQLSchemaGeneration graphQLSchemaGeneration;

    @GetMapping("/generate-graphql-schema")
    @ResponseBody
    public void generateGraphQLSchema() throws SQLException, IOException {
        graphQLSchemaGeneration.generateGraphQLSchema();
    }
}
