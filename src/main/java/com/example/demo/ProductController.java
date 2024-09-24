package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    @GetMapping("/products/{productId}")
    public Map<String, String> getProduct(@PathVariable int productId) {
        Map<String, String> response = new HashMap<>();
        response.put("id", String.valueOf(productId));
        response.put("name", productId + " name");
        return response;
    }
}