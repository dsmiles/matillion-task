package com.github.dsmiles.bestimage.repository;
//package codinpad.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.*;

@Repository
public class BestImageRepository {
    // Logger for logging messages
    private static final Logger logger = LoggerFactory.getLogger(BestImageRepository.class);

    private static final String API_KEY = System.getenv("API_KEY");
    private static final String API_URL = "https://api.pexels.com/v1/search?query=%s&per_page=%s";

    // RestTemplate for making HTTP requests
    private final RestTemplate restTemplate;

    /**
     * Constructor to initialize the RestTemplate.
     * @param restTemplate RestTemplate for making HTTP requests.
     */
    @Autowired
    public BestImageRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Finds the images by querying the external API.
     * @return JSON string of the API response or null if an error occurs.
     */
    public String find() {
        // Build the query string for the API request
        String apiUrl = String.format(API_URL, "science", "10");

        try {
            // Make the API request and get the response
            ResponseEntity<String> apiResponse = restTemplate.exchange(apiUrl, HttpMethod.GET, getRequest(), String.class);
            // Check if the response is successful
            if (apiResponse.getStatusCode().is2xxSuccessful()) {
                return apiResponse.getBody();
            } else {
                logger.error("Error from API: {}", apiResponse.getStatusCode());
                return null;
            }
        } catch (HttpClientErrorException e) {
            // Log any client exceptions that occur during the request
            logger.error("Client exception: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Creates the HTTP request with the necessary headers.
     * @return HttpEntity with the authorization header.
     */
    private HttpEntity<String> getRequest() {
        var headers = new HttpHeaders();
        headers.add("Authorization", API_KEY);
        var request = new HttpEntity<String>(headers);
        return request;
    }
}
