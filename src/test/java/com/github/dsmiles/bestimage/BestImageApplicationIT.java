package com.github.dsmiles.bestimage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BestImageApplicationIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getBestImageShouldReturnValidResponse() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/best-image", String.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        String responseBody = response.getBody();
        assertNotNull(responseBody);

        // Basic check to see if JSON fields are present not their actual values
        assertTrue(responseBody.contains("url"));
        assertTrue(responseBody.contains("photographer"));
        assertTrue(responseBody.contains("alt"));
    }
}