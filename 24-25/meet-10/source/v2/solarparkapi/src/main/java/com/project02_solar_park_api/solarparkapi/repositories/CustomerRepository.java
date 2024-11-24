package com.project02_solar_park_api.solarparkapi.repositories;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.mappers.CustomerRowMapper;
import com.project02_solar_park_api.solarparkapi.system.db.QueryBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate db;
    private final QueryBuilder db2;

    public CustomerRepository(JdbcTemplate db, QueryBuilder db2) {
        this.db = db;
        this.db2 = db2;
    }

    public boolean create(Customer customer) {

        return this.db2 .into(Customer.TABLE)
                        .withValue(Customer.columns.NAME, customer.getName())
                        .insert();
    }

    public List<Customer> fetchAll() {

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_customers WHERE is_active = 1");

        return this.db.query(query.toString(), new CustomerRowMapper());
    }

    public Customer fetch(int id) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_customers WHERE is_active = 1 AND id = " + id);
        ArrayList<Customer> collection = (ArrayList<Customer>) this.db.query(query.toString(), new CustomerRowMapper());

        if(collection.isEmpty()) {
            return null;
        }

        return collection.get(0);
    }

    public boolean update(Customer customer) {

        int resultCount = this.db2.updateTable(Customer.TABLE)
                .set(Customer.columns.NAME                  , customer.getName())
                .set(Customer.columns.NUMBER_OF_PROJECTS    , customer.getNumberOfProjects())
                .where(Customer.columns.IS_ACTIVE           ,"=", 1)
                .andWhere(Customer.columns.ID               , customer.getId())
                .update();

        if(resultCount > 1) {
            throw new RuntimeException("More than one customer with same id exists");
        }

        return resultCount == 1;
    }


    public boolean delete(int id) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE td_customers ")
                .append("SET is_active = 0 ")
                .append("WHERE id = ?");

        int resultCount = this.db.update(query.toString(), id);

        return resultCount == 1;
    }

}
