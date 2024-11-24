package com.project02_solar_park_api.solarparkapi.system.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class QueryBuilder {

    private final QueryProcessor queryProcessor;

    public QueryBuilder(QueryProcessor queryProcessor) {
        this.queryProcessor = queryProcessor;
    }

    // SELECT

    // INSERT
    public InsertQueryBuilder into(String tableName) {
        return new InsertQueryBuilder(this.queryProcessor, tableName);
    }

    // UPDATE
    public UpdateQueryBuilder updateTable(String tableName) {
        return new UpdateQueryBuilder(this.queryProcessor, tableName);
    }


    // DELETE
}
