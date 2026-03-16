package com.example.api.controller;

import com.example.api.model.Photo;
import com.example.api.service.PhotoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "*")
public class PhotoController {

    private final PhotoService service;

    public PhotoController(PhotoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Photo> getPhotos() {
        return service.getPhotos();
    }
}
