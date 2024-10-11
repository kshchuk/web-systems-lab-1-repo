package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    private final HashMap<Integer, String> products = new HashMap<>();

    @GetMapping("/products/{productId}")
    public Map<String, String> getProduct(@PathVariable int productId) {
        HashMap<String, String> response = new HashMap<>();
        products.put(productId, productId + " name");
        response.put("productId", Integer.toString(productId));
        response.put("productName", productId + " name");
        return response;
    }

    @GetMapping("/products")
    public Map<Integer, String> getProducts() {
        return products;
    }

}