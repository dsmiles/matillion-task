package com.github.dsmiles.bestimage.controller;
//package codinpad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import codinpad.service.BestImageService;
import com.github.dsmiles.bestimage.service.BestImageService;


@RestController public class BestImageController {

    BestImageService bestImageService;

    public BestImageController(BestImageService bestImageService) {
        this.bestImageService = bestImageService;
    }

    @GetMapping("/best-image")
    public ResponseEntity<String> getBestImage() {
        String response = bestImageService.find();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}