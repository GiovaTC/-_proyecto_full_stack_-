package com.example.api.service;

import com.example.api.model.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class PhotoService {

    private final WebClient webClient;

    public PhotoService() {
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");

    }

    public List<Photo> getPhotos() {

        Photo[] response = webClient.get()
                .uri("/photos?_limit=50")
                .retrieve()
                .bodyToMono(Photo[].class)
                .block();

        return Arrays.asList(response);
    }
}
