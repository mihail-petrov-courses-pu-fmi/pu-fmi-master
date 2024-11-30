package com.project02_solar_park_api.solarparkapi.system.db.operations;

import com.project02_solar_park_api.solarparkapi.system.db.QueryProcessor;

public class InsertQueryBuilder {

    private QueryProcessor queryProcessor;
    private String tableName;

    public InsertQueryBuilder(QueryProcessor queryProcessor, String tableName) {

        this.queryProcessor   = queryProcessor;
        this.tableName      = tableName;
        this.queryProcessor.initNewQueryOperation();
        this.queryProcessor.getQueryBuilder().append("INSERT INTO ").append(tableName);
    }

    public InsertQueryBuilder withValue(String columName, Object value) {

        this.queryProcessor.setQueryColumnValuePair(columName, value);
        return this;
    }

    // INSERT INTO table_name(col1...) VALUES(val..)
    public boolean insert() {

        String columnDefinition = String.join(",", this.queryProcessor.getColumnCollection());
        String valueDefinition  = String.join(",", this.queryProcessor.getPlaceholderCollection());

        this.queryProcessor.getQueryBuilder()
                .append("(")
                .append(columnDefinition)
                .append(") ")
                .append("VALUES")
                .append("(")
                .append(valueDefinition)
                .append(")");

        int resultCount = this.queryProcessor.processQuery();
        return resultCount > 0;
    }
}
