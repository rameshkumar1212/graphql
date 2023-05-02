package com.planview.graphql.utils;

import java.sql.Types;

public class TypeConverter {

    public static String convert(int sqlType) {
        switch (sqlType) {
            case Types.BOOLEAN:
                return "Boolean";
            case Types.INTEGER:
                return "Int";
            case Types.FLOAT:
            case Types.DOUBLE:
                return "Float";
            case Types.VARCHAR:
                return "String";
            // Add more cases for other PostgreSQL data types as needed
            default:
                throw new IllegalArgumentException("Unsupported SQL type: " + sqlType);
        }
    }
}
