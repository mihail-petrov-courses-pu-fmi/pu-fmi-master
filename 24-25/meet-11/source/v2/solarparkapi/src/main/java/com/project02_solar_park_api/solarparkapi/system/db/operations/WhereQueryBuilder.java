package com.project02_solar_park_api.solarparkapi.system.db.operations;

import com.project02_solar_park_api.solarparkapi.system.db.QueryProcessor;

public class WhereQueryBuilder<T> {

    private QueryProcessor queryProcessor;

    public WhereQueryBuilder(QueryProcessor queryProcessor) {
        this.queryProcessor = queryProcessor;
    }

    public T where(String columName, String operator, Object value) {

        this.queryProcessor.getQueryBuilder().append(" WHERE ");
        this.queryProcessor.buildColumnValuePair(columName, operator);
        this.queryProcessor.setQueryColumnValuePair(columName, value);

        return (T) this;
    }

    public T andWhere(String columName, String operator, Object value) {

        this.queryProcessor.getQueryBuilder().append(" AND ( ");
        this.queryProcessor.buildColumnValuePair(columName, operator);
        this.queryProcessor.getQueryBuilder().append(" ) ");
        this.queryProcessor.setQueryColumnValuePair(columName, value);
        return (T) this;
    }

    public T andWhere(String columName, Object value) {
        return this.andWhere(columName, "=", value);
    }

    public T orWhere(String columName, String operator, Object value) {

        this.queryProcessor.getQueryBuilder().append(" OR (");
        this.queryProcessor.buildColumnValuePair(columName, operator);
        this.queryProcessor.getQueryBuilder().append(" ) ");
        this.queryProcessor.setQueryColumnValuePair(columName, value);
        return (T) this;
    }

    public T orWhere(String columName, Object value) {
        return this.andWhere(columName, "=", value);
    }

    public T where(String columName, Object value) {
        return this.where(columName, "=", value);
    }
}
