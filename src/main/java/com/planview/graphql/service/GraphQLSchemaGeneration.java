package com.planview.graphql.service;

import java.io.IOException;
import java.sql.SQLException;

public interface GraphQLSchemaGeneration {

    void generateGraphQLSchema() throws SQLException, IOException;
}
