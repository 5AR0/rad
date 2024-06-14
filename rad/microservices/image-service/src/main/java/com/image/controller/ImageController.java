package com.image.controller;

import com.image.model.Image;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private static List<Image> images = Arrays.asList(
            new Image(1, "Simpson", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
            new Image(2, "Eric", "https://www.imdb.com/title/tt16283824/?ref_=hm_top_tt_i_2"),
            new Image(3, "The Office", "https://www.imdb.com/title/tt0386676/?ref_=hm_stp_pvs_piv_tt_i_7")
    );


    @GetMapping
    public List<Image> getImagaes() {
       // System.out.println("Call from gallery service!");
        return images;
    }

}

