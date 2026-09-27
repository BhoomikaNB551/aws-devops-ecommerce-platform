package com.devops.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the E-Commerce Application — Version 1.0 is Live!";
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}
