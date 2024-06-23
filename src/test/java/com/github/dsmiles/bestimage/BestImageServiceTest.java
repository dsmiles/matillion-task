package com.github.dsmiles.bestimage;
//package codinpad.service;

//import codinpad.repository.BestImageRepository;
import com.github.dsmiles.bestimage.repository.BestImageRepository;
import com.github.dsmiles.bestimage.service.BestImageService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BestImageServiceTest {

    @Mock
    private BestImageRepository bestImageRepository;

    @InjectMocks
    private BestImageService bestImageService;

    @Test
    public void testFind_ReturnsBestImageJson_WhenRepositoryReturnsMatches() throws IOException {
        String validJsonResponse = "{ \"photos\": ["
            + "{ \"src\": { \"original\": \"https://example.com/img1.jpg\" }, \"photographer\": \"Alice\", \"alt\": \"A cat\" },"
            + "{ \"src\": { \"original\": \"https://example.com/img2.jpg\" }, \"photographer\": \"Bob\", \"alt\": \"A dog\" },"
            + "{ \"src\": { \"original\": \"https://example.com/img3.jpg\" }, \"photographer\": \"Charlie\", \"alt\": \"A\" }"
            + "] }";
        when(bestImageRepository.find()).thenReturn(validJsonResponse);

        String expected = "{\"url\":\"https://example.com/img3.jpg\",\"photographer\":\"Charlie\",\"alt\":\"A\"}";
        String actual = bestImageService.find();

        assertEquals(expected, actual);

        verify(bestImageRepository).find();
    }

    @Test
    public void testFind_ReturnsNull_WhenRepositoryReturnsNoMatch() {
        String noMatchJsonResponse = "{\"page\":1,\"per_page\":10,\"photos\":[],\"total_results\":0}";
        when(bestImageRepository.find()).thenReturn(noMatchJsonResponse);

        String actual = bestImageService.find();

        assertNull(actual);

        verify(bestImageRepository).find();
    }

    @Test
    public void testFind_ReturnsNull_WhenRepositoryReturnsMalformedJson() {
        String malformedJsonResponse = "{ \"photos\": [ { \"src\": { \"original\": \"https://example.com/img1.jpg\" }, \"photographer\": \"Alice\"";
        when(bestImageRepository.find()).thenReturn(malformedJsonResponse);

        String actual = bestImageService.find();

        assertNull(actual);

        verify(bestImageRepository).find();
    }

    @Test
    public void testFind_ReturnsNull_WhenRepositoryReturnsEmptyJson() {
        String emptyJsonResponse = "{}";
        when(bestImageRepository.find()).thenReturn(emptyJsonResponse);

        String actual = bestImageService.find();

        assertNull(actual);

        verify(bestImageRepository).find();
    }

    @Test
    public void testFind_ReturnsNull_WhenRepositoryReturnsNull() {
        when(bestImageRepository.find()).thenReturn(null);

        String actual = bestImageService.find();

        assertNull(actual);

        verify(bestImageRepository).find();
    }
}
