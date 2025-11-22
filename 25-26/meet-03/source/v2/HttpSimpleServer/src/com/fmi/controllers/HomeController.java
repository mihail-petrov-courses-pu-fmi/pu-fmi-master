package com.fmi.controllers;

import labels.SimpleController;
import labels.SimpleGetMapping;

@SimpleController(url = "/home")
public class HomeController {

    public String process() {
        return "welcome home from Simple controller";
    };
}
