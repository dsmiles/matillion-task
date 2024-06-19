package com.github.dsmiles.bestimage;
//package codinpad.repository;

import com.github.dsmiles.bestimage.repository.BestImageRepository;
import com.github.dsmiles.bestimage.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class BestImageRepositoryTest {

    @InjectMocks
    private BestImageRepository bestImageRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFind() throws IOException {
        // Mock restTemplate.exchange() response by reading from a file
        String mockResponseBody = TestUtils.readResourceFile2("mock-response.json");
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(mockResponseBody, HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
            .thenReturn(mockResponseEntity);

        // Call the method
        String result = bestImageRepository.find();

        // Verify the result
        assertNotNull(result);
        assertTrue(result.contains("\"url\": \"https://images.pexels.com/photos/132477/pexels-photo-132477.jpeg\""));
        assertTrue(result.contains("\"photographer\": \"Anthony \uD83D\uDE42\""));
        assertTrue(result.contains("\"alt\": \"Clear Water Drops\""));
    }

}
