package com.fmi.solarparkapp.services;

import com.fmi.solarparkapp.models.base.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private JdbcTemplate jdbcTemplate;

    public CustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CustomerModel fetchAllCustomers() {

        // this.jdbcTemplate.
        // да извличаме данни от базата
        return null;
    }

    public boolean createNewCustomer(CustomerModel model) {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO td_customers");
        sb.append("(name, number_of_projects)");
        sb.append("VALUES ('");
        sb.append(model.getName()).append("',");
        sb.append(model.getNumberOfProjects());
        sb.append(");");

        String query = sb.toString();
        this.jdbcTemplate.execute(query);
        return true;
    }
}
