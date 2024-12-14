package com.project02_solar_park_api.solarparkapi.repositories;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
//    List<Project> findByCustomerAndIsActive(Customer customer, int isActive, Pageable pageable);
    List<Project> findByCustomerAndIsActive(Customer customer, int isActive);

    List<Project> findByCustomer_IdAndIsActive(int customerId, int i);
}
