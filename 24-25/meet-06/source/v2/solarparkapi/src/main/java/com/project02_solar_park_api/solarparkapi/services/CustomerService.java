package com.project02_solar_park_api.solarparkapi.services;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.mappers.CustomerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private JdbcTemplate db;

    public CustomerService(JdbcTemplate jdbc) {
        this.db = jdbc;
    }

    public boolean createCustomer(Customer customer) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO td_customers")
                .append("(name)")
                .append("VALUES")
                .append("('")
                .append(customer.getName())
                .append("')");

        this.db.execute(query.toString());
        return true;
    }

    public List<Customer> getAllCustomers() {

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_customers WHERE is_active = 1");

        return this.db.query(query.toString(), new CustomerRowMapper());
    }

    public Customer getCustomer(int id) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM td_customers WHERE is_active = 1 AND id = " + id);
        ArrayList<Customer> collection = (ArrayList<Customer>) this.db.query(query.toString(), new CustomerRowMapper());

        if(collection.isEmpty()) {
            return null;
        }

        return collection.get(0);
    }

    public boolean updateCustomer(Customer customer) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE td_customers ")
                .append("SET name = ?,")
                .append("number_of_projects = ? ")
                .append("WHERE is_active = 1 ")
                .append(" AND id = ?");

        int resultCount = this.db.update(query.toString(),
                        customer.getName(),
                        customer.getNumberOfProjects(),
                        customer.getId());

        if(resultCount > 1) {
            throw new RuntimeException("More than one customer with same id exists");
        }

        return resultCount == 1;
    }
}
