package com.project02_solar_park_api.solarparkapi.services;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        return this.db.query(query.toString(), (RowMapper<Customer>) (rs, rowNum) -> {

            Customer customer = new Customer();
            customer.setName(rs.getString("name"));
            customer.setId(rs.getInt("id"));
            customer.setNumberOfProjects(rs.getInt("number_of_projects"));

            return customer;
        });
    }
}
