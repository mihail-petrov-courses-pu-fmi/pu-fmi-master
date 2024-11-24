package com.project02_solar_park_api.solarparkapi.system.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class QueryBuilder {

    private final JdbcTemplate jdbcTemplate;

    private StringBuilder queryBuilder;
    private ArrayList<String> columnCollection;
    private ArrayList<String> placeholderCollection;
    private ArrayList<Object> valueCollection;

    public QueryBuilder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // INSERT INTO table_name(col1...) VALUES(val..)
    public boolean insert() {

        String columnDefinition = String.join(",", this.columnCollection);
        String valueDefinition  = String.join(",", this.placeholderCollection);

        queryBuilder.append("(").append(columnDefinition).append(") ")
                    .append("VALUES")
                    .append("(").append(valueDefinition).append(")");

        String sqlQuery = this.queryBuilder.toString();

        int resultCount = this.jdbcTemplate.update(sqlQuery, this.valueCollection.toArray());
        return resultCount > 0;
    }

    public QueryBuilder into(String tableName) {

        this.columnCollection      = new ArrayList<>();
        this.placeholderCollection = new ArrayList<>();
        this.valueCollection       = new ArrayList<>();

        this.queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ").append(tableName);

        return this;
    }

    public QueryBuilder withValue(String columName, Object value) {

        columnCollection.add(columName);
        placeholderCollection.add("?");
        valueCollection.add(value);

        return this;
    }


    // UPDATE table_name SET col = ? WHERE


    // SELECT * FROM table_name WHERE column = 1
    // UPDATE table_name SET col = ? WHERE col = 1

}
