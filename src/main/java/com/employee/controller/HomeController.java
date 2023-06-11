package com.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path="/welcome")
    public String greetingHandler(){
        return "Hello how are you ???";
    }
}