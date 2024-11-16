package org.example.controllers;

import org.example.steriotypes.*;

@Controller(method = "GET", endpoint = "/customer")
public class CustomerController {

    @GetMapping("/customer")
    public String fetchAllCustomers() {
        return "Customer info - GET Request";
    }

    @GetMapping("/customer/{id}")
    public String fetchSingeCustomer() {
        return "Customer SINGLE info - GET Request with path variables";
    }

    @PostMapping("/customer")
    public String createNewCustomer() {
        return "Customer info - POST Request";
    }

    @PutMapping("/customer")
    public String updateExistingCustomer() {
        return "Customer info - PUT Request";
    }

    @DeleteMapping("/customer")
    public String removeExistingCustomer() {
        return "Customer info - DELETE Request";
    }
}
