package com.example.inventory_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        // This will look for index.html in src/main/resources/static
        return "index";
    }
}