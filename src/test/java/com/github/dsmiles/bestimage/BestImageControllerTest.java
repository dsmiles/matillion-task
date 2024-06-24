package com.github.dsmiles.bestimage;

import com.github.dsmiles.bestimage.controller.BestImageController;
import com.github.dsmiles.bestimage.service.BestImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BestImageControllerTest {

    @Mock
    private BestImageService bestImageService;

    @InjectMocks
    private BestImageController bestImageController;

    @Test
    public void testGetBestImage_ReturnsBestImage_WhenResultContainValidJson() {
        String mockResponse = "{\"url\": \"https://example.com/bestimage.jpg\", \"photographer\": \"John Doe\", \"alt\": \"Nature\"}";
        when(bestImageService.find()).thenReturn(mockResponse);

        ResponseEntity<String> responseEntity = bestImageController.getBestImage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());

        verify(bestImageService).find();
    }

    @Test
    public void testGetBestImage_ReturnsNotFound_WhenResultContainsNull() {
        when(bestImageService.find()).thenReturn(null);

        ResponseEntity<String> responseEntity = bestImageController.getBestImage();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No image found", responseEntity.getBody());

        verify(bestImageService).find();
    }
}
