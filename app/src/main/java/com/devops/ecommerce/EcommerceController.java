package com.devops.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

    @GetMapping("/")
    public String home() {
        return "AWS DevOps E-Commerce Application v1 is Running!";
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}
