package com.github.dsmiles.bestimage;
//package codinpad.controller;

//import codinpad.service.BestImageService;
import com.github.dsmiles.bestimage.controller.BestImageController;
import com.github.dsmiles.bestimage.service.BestImageService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BestImageControllerTest {

    @Mock
    private BestImageService bestImageService;

    @InjectMocks
    private BestImageController bestImageController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBestImage() {
        // Mock service response
        String mockResponse = "{\"url\": \"http://example.com/bestimage.jpg\", \"photographer\": \"John Doe\", \"alt\": \"Nature\"}";
        when(bestImageService.find()).thenReturn(mockResponse);

        // Call the controller method
        ResponseEntity<String> responseEntity = bestImageController.getBestImage();

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

}
