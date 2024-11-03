package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/{productId}")
    public Map<String, String> getProduct(@PathVariable int productId) {
        Product product = productRepository.findById(productId).orElse(null);
        HashMap<String, String> response = new HashMap<>();

        if (product != null) {
            response.put("productId", Integer.toString(product.getId()));
            response.put("productName", product.getName());
        } else {
            response.put("error", "Product not found");
        }



        return response;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
