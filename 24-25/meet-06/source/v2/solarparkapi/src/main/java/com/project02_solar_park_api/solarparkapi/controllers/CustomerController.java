package com.project02_solar_park_api.solarparkapi.controllers;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.http.AppResponse;
import com.project02_solar_park_api.solarparkapi.services.CustomerService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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

}
