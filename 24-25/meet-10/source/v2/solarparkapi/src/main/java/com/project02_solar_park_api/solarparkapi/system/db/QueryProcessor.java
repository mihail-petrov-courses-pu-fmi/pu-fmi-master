package com.project02_solar_park_api.solarparkapi.system.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QueryProcessor {

    private final JdbcTemplate jdbcTemplate;

    private StringBuilder queryBuilder;
    private ArrayList<String> columnCollection;
    private ArrayList<String> placeholderCollection;
    private ArrayList<Object> valueCollection;

    public QueryProcessor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public StringBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public ArrayList<String> getColumnCollection() {
        return columnCollection;
    }

    public ArrayList<String> getPlaceholderCollection() {
        return placeholderCollection;
    }

    public ArrayList<Object> getValueCollection() {
        return valueCollection;
    }

    public int processQuery() {

        String sqlQuery = this.queryBuilder.toString();
        return this.jdbcTemplate.update(sqlQuery, this.valueCollection.toArray());
    }

    public void initNewQueryOperation() {

        this.columnCollection      = new ArrayList<>();
        this.placeholderCollection = new ArrayList<>();
        this.valueCollection       = new ArrayList<>();
        this.queryBuilder          = new StringBuilder();
    }

    public void setQueryColumnValuePair(String columName, Object value) {

        columnCollection.add(columName);
        placeholderCollection.add("?");
        valueCollection.add(value);
    }

    public void buildColumnValuePair(String columName, String operator) {

        this.queryBuilder.append(columName)
                .append(operator)
                .append("?");
    }

    public void buildColumnValuePair(String columName) {
        this.buildColumnValuePair(columName, "=");
    }
}
