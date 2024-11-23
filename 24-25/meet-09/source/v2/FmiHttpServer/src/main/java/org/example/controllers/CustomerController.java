package org.example.controllers;

import org.example.services.NestedLeve1;
import org.example.services.CustomerService;
import org.example.steriotypes.*;
import org.example.system.ResponseMessage;

@Controller(method = "GET", endpoint = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private NestedLeve1 businesService;

    @GetMapping("/customer")
    public String fetchAllCustomers() {
        return this.customerService.hello();
    }

    @GetMapping("/customer/{id}/projects/{project_id}")
    public ResponseMessage fetchSingeCustomer(@PathVariable("id") int id,
                                              @PathVariable("project_id") int projectId) {
        return new ResponseMessage(
                "Customer SINGLE info - " + id + " with project" + projectId,
                404
        );
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
