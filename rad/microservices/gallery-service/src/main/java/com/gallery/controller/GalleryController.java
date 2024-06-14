package com.gallery.controller;


import com.gallery.model.Gallery;
import com.gallery.model.Image;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/gallery")
@RefreshScope
public class GalleryController {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    private static final String SERVICE_NAME = "gallery-service";

    @GetMapping("/{id}")
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "galleryServiceFallback")
    public Gallery getGallery(@PathVariable int id) {

        Gallery gallery = new Gallery();
        gallery.setId(id);

        List<Object> images = restTemplate.getForObject("http://IMAGE-SERVICE/images", List.class);
        restTemplate.toString();

        gallery.setImages(images);
        return gallery;
    }


    public Gallery galleryServiceFallback(Exception e) {
        System.out.println("Fallback Method invoked!!!");

        List<Object> images = Arrays.asList(
                new Image(1, "This is a fallback method for gallery service.", "")
        );
        Gallery gallery = new Gallery(1, images);
        return gallery;
    }

}


