package com.example.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StaticContentController {

    @GetMapping("/static/image")
    public ResponseEntity<byte[]> getStaticImage() throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/example.jpg");

        byte[] imageBytes = imgFile.getInputStream().readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
