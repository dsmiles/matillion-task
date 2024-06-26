package com.github.dsmiles.bestimage.controller;

import com.github.dsmiles.bestimage.service.BestImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to handle HTTP requests related to the best image.
 */
@RestController
public class BestImageController {
    // Logger for logging messages
    private static final Logger logger = LoggerFactory.getLogger(BestImageController.class);

    // Service to find the best image
    private final BestImageService bestImageService;

    /**
     * Constructor to initialize the BestImageService.
     * @param bestImageService Service to find the best image.
     */
    public BestImageController(BestImageService bestImageService) {
        this.bestImageService = bestImageService;
    }

    /**
     * Endpoint to get the best image.
     * @return ResponseEntity containing the best image JSON string or an error message.
     */
    @GetMapping("/best-image")
    public ResponseEntity<String> getBestImage() {
        // Find the best image using the service
        String response = bestImageService.find();
        if (response == null) {
            // Log a warning if no image is found
            logger.warn("No image(s) found in JSON response");
            return new ResponseEntity<>("No image found", HttpStatus.NOT_FOUND);
        }
        // Return the best image JSON string with an HTTP OK status
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
