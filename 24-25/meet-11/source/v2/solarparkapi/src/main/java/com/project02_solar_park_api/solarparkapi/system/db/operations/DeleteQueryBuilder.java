package com.project02_solar_park_api.solarparkapi.system.db.operations;

import com.project02_solar_park_api.solarparkapi.system.db.QueryProcessor;

public class DeleteQueryBuilder extends WhereQueryBuilder<DeleteQueryBuilder> {

    private QueryProcessor queryProcessor;
    private String tableName;

    public DeleteQueryBuilder(QueryProcessor queryProcessor, String tableName) {

        super(queryProcessor);
        this.queryProcessor = queryProcessor;
        this.queryProcessor.initNewQueryOperation();
        this.queryProcessor.getQueryBuilder().append("DELETE FROM ").append(tableName);
    }

    public int delete() {
        return this.queryProcessor.processQuery();
    }
}
