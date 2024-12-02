package com.project02_solar_park_api.solarparkapi.controllers;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.http.AppResponse;
import com.project02_solar_park_api.solarparkapi.services.CustomerService;
import com.project02_solar_park_api.solarparkapi.services.ProjectService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class CustomerController {

    private CustomerService customerService;
    private ProjectService projectService;

    public CustomerController(
            CustomerService customerService,
            ProjectService projectService
    ) {
        this.customerService = customerService;
        this.projectService = projectService;
    }

    @PostMapping("/customers")
    public ResponseEntity<?> createNewCustomer(@RequestBody Customer customer) {

        HashMap<String, Object> response = new HashMap<>();

        if(this.customerService.createCustomer(customer)) {

            return AppResponse.success()
                    .withMessage("Customer created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Customer could not be created")
                .build();
    }

    @GetMapping("/customers")
    public ResponseEntity<?> fetchAllCustomers() {

        ArrayList<Customer> collection = (ArrayList<Customer>) this.customerService.getAllCustomers();

        return AppResponse.success()
                        .withData(collection)
                        .build();
    }

    // Select single
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> fetchSingleCustomer(@PathVariable int id) {

        Customer responseCustomer =  this.customerService.getCustomer(id);

        if(responseCustomer == null) {
            return AppResponse.error()
                        .withMessage("Customer data not found")
                        .build();
        }

        return AppResponse.success()
                    .withDataAsArray(responseCustomer)
                    .build();
    }

    @GetMapping("/customers/{id}/projects")
    public ResponseEntity<?> fetchAllProjectsForCustomer(@PathVariable int id) {

        var result = this.projectService.getAllByCustomer(id);

        return AppResponse.success()
                .withMessage("Fetch successful")
                .withData(result)
                .build();
    }


    // Update

    @PutMapping("/customers")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        boolean isUpdateSuccessful =  this.customerService.updateCustomer(customer);

        if(!isUpdateSuccessful) {
            return AppResponse.error()
                    .withMessage("Customer data not found")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Update successful")
                .build();
    }

    // Delete
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> removeCustomer(@PathVariable int id) {

        boolean isUpdateSuccessful =  this.customerService.removeCustomer(id);

        if(!isUpdateSuccessful) {
            return AppResponse.error()
                    .withMessage("Customer not found")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Remove successful")
                .build();
    }
}
