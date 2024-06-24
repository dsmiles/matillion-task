package com.github.dsmiles.bestimage;

import com.github.dsmiles.bestimage.repository.BestImageRepository;
import com.github.dsmiles.bestimage.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BestImageRepositoryTest {

    @Mock
    private RestTemplate mockRestTemplate;

    @InjectMocks
    private BestImageRepository bestImageRepository ;

    @Test
    public void testFind_ReturnsData_WhenApiResponseIsSuccessful() throws IOException {
        String mockApiResponse = TestUtils.readResourceFile("mock-response.json");

        ResponseEntity<String> response = new ResponseEntity<>(mockApiResponse, HttpStatus.OK);
        when(mockRestTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(response);

        String actual = bestImageRepository.find();

        assertEquals(mockApiResponse, actual);
    }

    @Test
    public void testFind_ReturnsNull_WhenApiResponseIsUnsuccessful() {
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(mockRestTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenReturn(response);

        String result = bestImageRepository.find();

        assertNull(result);
    }

    @Test
    public void testFind_ReturnsNull_WhenRestTemplateExchangeThrowsException() {
        when(mockRestTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
            .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        String result = bestImageRepository.find();

        assertNull(result);
    }
}
