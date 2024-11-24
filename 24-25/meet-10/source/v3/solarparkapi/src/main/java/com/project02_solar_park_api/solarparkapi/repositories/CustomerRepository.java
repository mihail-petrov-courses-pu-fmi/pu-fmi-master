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

    private final QueryBuilder<Customer> db;

    public CustomerRepository(QueryBuilder<Customer> db) {
        this.db = db;
    }

    public boolean create(Customer customer) {

        return this.db .into(Customer.TABLE)
                        .withValue(Customer.columns.NAME, customer.getName())
                        .insert();
    }

    public List<Customer> fetchAll() {

        return this.db.selectAll()
                        .from(Customer.TABLE)
                        .where(Customer.columns.IS_ACTIVE, 1)
                        .fetchAll(new CustomerRowMapper());
    }

    public Customer fetch(int id) {

        return this.db.selectAll().from(Customer.TABLE)
                        .where(Customer.columns.IS_ACTIVE, 1)
                        .andWhere(Customer.columns.ID, id)
                        .fetch(new CustomerRowMapper());
    }

    public boolean update(Customer customer) {

        int resultCount = this.db.updateTable(Customer.TABLE)
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

        int resultCount = this.db.deleteTable(Customer.TABLE)
                                  .where(Customer.columns.ID, id)
                                  .delete();
        return resultCount == 1;
    }
}
