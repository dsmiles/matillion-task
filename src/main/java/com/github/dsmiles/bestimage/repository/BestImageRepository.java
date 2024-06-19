package com.github.dsmiles.bestimage.repository;
//package codinpad.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class BestImageRepository {

    private static final String API_KEY = System.getenv("API_KEY");
    private static final String API_URL = "https://api.pexels.com/v1/search?query=%s&per_page=%s";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String find() {
        var restTemplate = new RestTemplate();

        // build the query string
        String apiUrl = String.format(API_URL, "science", 10);

        ResponseEntity<String> apiResponse = restTemplate.exchange(apiUrl, HttpMethod.GET, getRequest(), String.class);

        // Parse the API response and extract best image
        return parseResponse(apiResponse.getBody());
    }

    private HttpEntity<String> getRequest() {
        var headers = new HttpHeaders();
        headers.add("Authorization", API_KEY);
        var request = new HttpEntity<String>(headers);
        return request;
    }

    /**
     * Parses the JSON response body to find the best image based on
     * specific criteria.
     *
     * @param responseBody The JSON response body containing photo
     *                     information.
     * @return A JSON-formatted string representing the best image
     * found, including its URL,  photographer, and alt text. Returns
     * {@code null} if there are parsing errors or no suitable image
     * is found.
     */
    private String parseResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode photosNode = root.path("photos");

            // Initialize variables to track the best image
            String bestImageUrl = null;
            String bestPhotographer = null;
            String bestAlt = null;
            int shortestAltLength = Integer.MAX_VALUE;
            int shortestPhotographerLength = Integer.MAX_VALUE;

            // Iterate through photos to find the best image based on criteria
            for (JsonNode photoNode : photosNode) {
                String imageUrl = photoNode.path("src").path("original").asText();
                String photographer = photoNode.path("photographer").asText();
                String alt = photoNode.path("alt").asText();

                // Check if current image has a shorter alt or photographer name
                if (alt.length() < shortestAltLength ||
                    (alt.length() == shortestAltLength && photographer.length() < shortestPhotographerLength)) {
                    bestImageUrl = imageUrl;
                    bestPhotographer = photographer;
                    bestAlt = alt;
                    shortestAltLength = alt.length();
                    shortestPhotographerLength = photographer.length();
                }
            }

            // Construct JSON string with the best image data
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");
            jsonBuilder.append("\"url\": \"").append(bestImageUrl).append("\", ");
            jsonBuilder.append("\"photographer\": \"").append(bestPhotographer).append("\", ");
            jsonBuilder.append("\"alt\": \"").append(bestAlt).append("\"");
            jsonBuilder.append("}");

            // Return the best image found
            return jsonBuilder.toString();

        } catch (Exception e) {
            // Handle parsing exceptions or any other errors
            // TODO
            // Should include detailed logging here
            e.printStackTrace();
            return null;
        }
    }
}


