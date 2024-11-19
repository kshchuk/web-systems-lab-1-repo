package com.example.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", key = "#productId")
    @GetMapping("/{productId}")
    public Map<String, String> getProduct(@PathVariable int productId) {
        try {
            Thread.sleep(2000); // Simulate a slow request
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

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


    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product productDetails) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setName(productDetails.getName());
                    Product updatedProduct = productRepository.save(product);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int productId) {
        return productRepository.findById(productId)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
