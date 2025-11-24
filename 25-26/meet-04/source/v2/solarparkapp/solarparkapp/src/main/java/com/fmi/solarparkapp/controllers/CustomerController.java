package com.fmi.solarparkapp.controllers;

import com.fmi.solarparkapp.http.AppResponse;
import com.fmi.solarparkapp.models.base.CustomerModel;
import com.fmi.solarparkapp.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//
//    GET      /customers --> върни всички къстъмъри

    @GetMapping("/customers")
    public ResponseEntity<?> fetchAllCustomers() {

        // искам да получа колекция от всички потребители, които се намират в базата ми данни.
        ArrayList<CustomerModel> collection = (ArrayList<CustomerModel>) this.customerService.fetchAllCustomers();

        return AppResponse.success()
                .withData(collection)
                .send();
    }

//    GET      /customers/:id --> върни конкретен потребител
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> fetchCustomerById(@PathVariable int id) {

        CustomerModel model = this.customerService.fetchCustomerById(id);

        if(model == null) {
            return AppResponse.error()
                    .withCode(HttpStatus.NOT_FOUND)
                    .withMessage("Customer not found")
                    .send();
        }

        return AppResponse.success()
                .withData(model)
                .send();
    }


//    POST     /customers
    @PostMapping("/customers")
    public ResponseEntity<?> createNewCustomer(@RequestBody CustomerModel customer) {

        if(this.customerService.createNewCustomer(customer)) {

            return AppResponse.success()
                    .withMessage("New customer created")
                    .send();
        }

        return AppResponse.error()
                .withMessage("Cannot create customer with this setup")
                .send();
    }

//    PUT      /customers/:id
//    DELETE   /customers/:id
}
