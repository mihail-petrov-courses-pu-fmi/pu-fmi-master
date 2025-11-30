package com.fmi.rentacar.system.db.operations;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class InsertQueryBuilder {

    private JdbcTemplate jdbcTemplate;
    private String tableName;
    private StringBuilder query;

    private ArrayList<String> columnCollection;
    private ArrayList<String> placeholderCollection;
    private ArrayList<Object> valueCollection;

    public InsertQueryBuilder(
            JdbcTemplate jdbcTemplate,
            String tableName
    ) {
        this.jdbcTemplate   = jdbcTemplate;
        this.tableName      = tableName;
        this.query          = new StringBuilder();
        this.query.append("INSERT INTO ").append(tableName);

        this.columnCollection       = new ArrayList<>();
        this.placeholderCollection  = new ArrayList<>();
        this.valueCollection        = new ArrayList<>();
    }
    // INSERT INTO table(c1,c2,c3)
    // VALUES(v1, v2,v3)
    public InsertQueryBuilder withValue(String key, Object value) {

        this.columnCollection.add(key);
        this.placeholderCollection.add("?");
        this.valueCollection.add(value);

        return this;
    }

    public boolean insert() {

        String columnDefinition         = String.join(",", this.columnCollection);
        String placeholderDefinition    = String.join(",", this.placeholderCollection);

        // създаване на крайния резултат
        String resultQuery = this.query
                .append("(")
                .append(columnDefinition)
                .append(") ")
                .append("VALUES")
                .append("(")
                .append(placeholderDefinition)
                .append(")").toString();

        int queryResult = this.jdbcTemplate.update(resultQuery, this.valueCollection.toArray());
        return queryResult > 0;
    }
}
