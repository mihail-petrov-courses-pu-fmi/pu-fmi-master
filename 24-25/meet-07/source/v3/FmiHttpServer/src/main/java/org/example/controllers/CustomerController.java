package org.example.controllers;

import org.example.steriotypes.*;

@Controller(method = "GET", endpoint = "/customer")
public class CustomerController {

    @GetMapping("/customer")
    public String fetchAllCustomers() {
        return "Customer info - GET Request";
    }

    @GetMapping("/customer/{id}/projects/{project_id}")
    public String fetchSingeCustomer(@PathVariable("id") int id,
                                     @PathVariable("project_id") int projectId) {
        return "Customer SINGLE info - " + id + " with project" + projectId;
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
