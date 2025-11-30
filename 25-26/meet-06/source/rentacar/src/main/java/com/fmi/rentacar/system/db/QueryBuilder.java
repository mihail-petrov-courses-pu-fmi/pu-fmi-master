package com.fmi.rentacar.system.db;

import com.fmi.rentacar.models.CarModel;
import com.fmi.rentacar.system.db.operations.InsertQueryBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueryBuilder {

    private JdbcTemplate jdbcTemplate;

    public QueryBuilder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SELECT
    // UPDATE
    // DELETE
    // INSERT
    public InsertQueryBuilder into(String table) {
        return new InsertQueryBuilder(this.jdbcTemplate, table);
    }
}
