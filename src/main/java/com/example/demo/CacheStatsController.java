package com.example.demo;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheStatsController {

    private final CaffeineCacheManager cacheManager;

    @Autowired
    public CacheStatsController(CaffeineCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping("/stats")
    public String getCacheStats() {
        // Ensure the cache exists
        if (cacheManager.getCache("products") == null) {
            return "Cache 'products' not found.";
        }

        // Cast the native cache to Caffeine cache
        Cache<Object, Object> nativeCache = (Cache<Object, Object>) cacheManager.getCache("products").getNativeCache();

        // Fetch and return cache stats
        CacheStats stats = nativeCache.stats();
        return stats.toString();
    }
}
