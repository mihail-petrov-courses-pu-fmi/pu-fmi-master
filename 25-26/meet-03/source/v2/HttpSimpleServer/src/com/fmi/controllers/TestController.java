package com.fmi.controllers;

import labels.SimpleController;

@SimpleController(url = "/test")
public class TestController {

    public String process() {
        return "This is test controller";
    }
}
