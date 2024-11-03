package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExternalApiController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/external/{postId}")
    public String getExternalPost(@PathVariable int postId) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + postId;
        return restTemplate.getForObject(url, String.class);
    }
}
