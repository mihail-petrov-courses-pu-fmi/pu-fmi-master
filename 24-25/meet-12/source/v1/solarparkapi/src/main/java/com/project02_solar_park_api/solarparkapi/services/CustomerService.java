package com.project02_solar_park_api.solarparkapi.services;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.repositories.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepository;

    public CustomerService(CustomerRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean createCustomer(Customer customer) {
        this.customerRepository.save(customer);
        return true;
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findByIsActive(1);
    }

    public Customer getCustomer(int id) {
        return this.customerRepository.findByIdAndIsActive(id,1);
    }

    public boolean updateCustomer(Customer customer) {
        this.customerRepository.save(customer);
        return true;
    }

    public boolean removeCustomer(int id) {
        Customer customer = getCustomer(id);
        if(customer!= null){
            customer.setIsActive(0);
            this.customerRepository.save(customer);
            return true;
        }

        return false;
    }
}
