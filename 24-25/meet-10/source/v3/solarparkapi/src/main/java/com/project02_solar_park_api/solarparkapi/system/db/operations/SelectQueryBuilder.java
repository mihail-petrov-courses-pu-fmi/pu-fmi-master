package com.project02_solar_park_api.solarparkapi.system.db.operations;

import com.project02_solar_park_api.solarparkapi.system.db.QueryProcessor;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class SelectQueryBuilder<T> extends WhereQueryBuilder<SelectQueryBuilder<T>> {

    private final QueryProcessor<T> queryProcessor;

    public SelectQueryBuilder(QueryProcessor<T> queryProcessor, String ...cols) {
        super(queryProcessor);
        this.queryProcessor = queryProcessor;

        this.queryProcessor.initNewQueryOperation();
        this.queryProcessor.getQueryBuilder()
                            .append("SELECT ")
                            .append(String.join(", ", cols));
    }

    public SelectQueryBuilder<T> from(String tableName) {

        this.queryProcessor.getQueryBuilder().append(" FROM ")
                                             .append(tableName);

        return this;
    }

    public List<T> fetchAll(RowMapper<T> mapper) {
        return this.queryProcessor.processSelectList(mapper);
    }

    public T fetch(RowMapper<T> mapper) {

        try {
            return this.fetchAll(mapper).get(0);
        }
        catch (Exception e) {
            return null;
        }
    }
}
