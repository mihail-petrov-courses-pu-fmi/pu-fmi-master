package org.example.controllers;

import org.example.steriotypes.Controller;
import org.example.steriotypes.GetMapping;

@Controller(method = "GET", endpoint = "/customer")
public class CustomerController {

    @GetMapping("/customer")
    public String index() {
        return "Customer info";
    }


}
