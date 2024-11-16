package org.example.controllers;

import org.example.steriotypes.Controller;
import org.example.steriotypes.GetMapping;

@Controller(method = "GET", endpoint = "/home")
public class HomeController {

    @GetMapping("/home")
    public String index() {
        return "Home content";
    }
}
