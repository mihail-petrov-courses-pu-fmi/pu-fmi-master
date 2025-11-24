package com.fmi.solarparkapp.controllers;

import com.fmi.solarparkapp.models.base.CustomerModel;
import com.fmi.solarparkapp.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public CustomerModel fetchAllCustomers() {
        return null;
    }

//    GET      /customers/:id


//    POST     /customers
    @PostMapping("/customers")
    public ResponseEntity<?> createNewCustomer(@RequestBody CustomerModel customer) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if(this.customerService.createNewCustomer(customer)) {

            responseMap.put("status", "success");
            responseMap.put("code", HttpStatus.OK.value());
            responseMap.put("message", "New customer successfully created");

            ResponseEntity<Object> successResponse = new ResponseEntity<>(responseMap, HttpStatus.OK);
            return successResponse;
        }

        responseMap.put("status", "error");
        responseMap.put("code", HttpStatus.BAD_REQUEST.value());
        responseMap.put("message", "Cannot create customer with this setup");

        ResponseEntity<Object> errorResponse = new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        return errorResponse;
    }


//    PUT      /customers/:id
//    DELETE   /customers/:id
}
