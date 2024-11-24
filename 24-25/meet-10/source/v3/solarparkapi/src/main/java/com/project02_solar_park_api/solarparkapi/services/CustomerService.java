package com.project02_solar_park_api.solarparkapi.services;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.mappers.CustomerRowMapper;
import com.project02_solar_park_api.solarparkapi.repositories.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean createCustomer(Customer customer) {
        return this.customerRepository.create(customer);
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.fetchAll();
    }

    public Customer getCustomer(int id) {
        return this.customerRepository.fetch(id);
    }

    public boolean updateCustomer(Customer customer) {
        return this.customerRepository.update(customer);
    }

    public boolean removeCustomer(int id) {
        return this.customerRepository.delete(id);
    }
}
