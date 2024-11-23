package org.example.controllers;

import org.example.services.BusinesService;
import org.example.services.CustomerService;
import org.example.steriotypes.*;

@Controller(method = "GET", endpoint = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BusinesService businesService;

    @GetMapping("/customer")
    public String fetchAllCustomers() {

        double amount = this.businesService.returnOfInvestmentAmount();
        return this.customerService.hello() + " " + amount;
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
