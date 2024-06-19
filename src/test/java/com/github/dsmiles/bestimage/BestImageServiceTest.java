package com.github.dsmiles.bestimage;
//package codinpad.service;

//import codinpad.repository.BestImageRepository;
import com.github.dsmiles.bestimage.repository.BestImageRepository;
import com.github.dsmiles.bestimage.service.BestImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BestImageServiceTest {

    @Mock
    private BestImageRepository bestImageRepository;

    @InjectMocks
    private BestImageService bestImageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFind() {
        // Mock repository response
        String mockResponse = "{\"url\": \"http://example.com/bestimage.jpg\", \"photographer\": \"John Doe\", \"alt\": \"Nature\"}";
        when(bestImageRepository.find()).thenReturn(mockResponse);

        // Call the service method
        String result = bestImageService.find();

        // Verify the result
        assertEquals(mockResponse, result);
    }
}

