package com.project02_solar_park_api.solarparkapi.repositories;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findByIsActive(int isActive);
    Customer findByIdAndIsActive(int id, int isActive);
}
