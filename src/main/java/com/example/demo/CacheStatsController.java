package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/cache")
public class CacheStatsController {

    private final RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public CacheStatsController(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/stats")
    public String getCacheStats() {
        // Get all keys from the "products" cache
        Set<Object> keys = redisTemplate.keys("products:*");

        if (keys == null || keys.isEmpty()) {
            return "No keys found in the 'products' cache.";
        }

        // Fetch memory usage (optional, requires Redis monitoring tools)
        long totalKeys = keys.size();

        return String.format("Cache 'products' contains %d keys.", totalKeys);
    }
}
