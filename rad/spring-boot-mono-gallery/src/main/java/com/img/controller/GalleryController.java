package com.img.controller;


import com.img.model.Gallery;
import com.img.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    private Environment env;

    public static List<Image> images = Arrays.asList(
            new Image(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
            new Image(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
            new Image(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112")
    );

    @GetMapping
    public String home() {
        // This is useful for debugging
        // When having multiple instance of gallery service running at different ports.
        // We load balance among them, and display which instance received the request.
        return "Hello from Gallery running at port: " + env.getProperty("local.server.port");
    }


    @RequestMapping("/images")
    public List<Image> getImagaes() {
        return images;
    }


    @GetMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {

        Gallery gallery = new Gallery();
        gallery.setId(id);
        gallery.setImages(images);

        System.out.println(gallery.toString());

        return gallery;
    }
}


    /**
     *
     *  The image service acts as a data source for images, each image has an id,title, and url.
     *
     *  So, for example, the gallery service calls image service to get a list of all images, or maybe only images created during a specific year.
     *
     */
