package com.planview.graphql.service;

import com.planview.graphql.utils.TypeConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class GraphQLSchemaGenerationImpl implements GraphQLSchemaGeneration {

    @Autowired
    DataSource dataSource;

    @Override
    public void generateGraphQLSchema() throws SQLException, IOException {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
        while (tables.next()) {
            String tableName = CaseUtils.toCamelCase(tables.getString("TABLE_NAME"), false, '_');
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            StringBuilder fields = new StringBuilder();
            fields.append("type ").append(tableName).append(" {\n");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String fieldName = CaseUtils.toCamelCase(columnName, false, '_');
                int columnType = columns.getInt("DATA_TYPE");
                String fieldDataType = TypeConverter.convert(columnType);
                fields.append(StringUtils.SPACE).append(fieldName)
                        .append(":").append(StringUtils.SPACE)
                        .append(fieldDataType).append("\n");

            }
            fields.append("}");
            File schemaFile = new File("src/main/resources/schema/" + tableName + ".graphql");
            FileUtils.write(schemaFile, fields.toString());
        }

    }
}
