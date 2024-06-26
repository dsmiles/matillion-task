package com.github.dsmiles.bestimage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.github.dsmiles.bestimage.repository.BestImageRepository;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class BestImageService {
    // Logger for logging messages
    private static final Logger logger = LoggerFactory.getLogger(BestImageService.class);

    // Repository to fetch image data
    private final BestImageRepository bestImageRepository;

    // ObjectMapper for JSON processing
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Constructor to initialize the BestImageRepository
    public BestImageService(BestImageRepository bestImageRepository) {
        this.bestImageRepository = bestImageRepository;
    }

    /**
     * Finds the best image from the repository and returns it as a JSON string.
     * @return JSON string of the best image or null if an error occurs.
     */
    public String find() {
        // Fetch the response body from the repository
        String responseBody = bestImageRepository.find();
        if (responseBody == null || responseBody.isEmpty()) {
            logger.error("Response body is null or empty");
            return null;
        }

        // Find the best image node from the response
        JsonNode bestImageNode = findBestImage(responseBody);
        if (bestImageNode == null) {
            logger.error("No suitable image found in the response body");
            return null;
        }

        // Construct a JSON string from the best image node
        String bestImageJson = constructJsonString(bestImageNode);
        if (bestImageJson == null) {
            logger.error("Error constructing JSON for the best image");
        }

        return bestImageJson;
    }

    /**
     * Finds the best image from the given response body based on specific criteria.
     *
     * I didn't like my original method for finding the best image, too complicated
     * checking string lengths of various nodes. Looked up other ways of doing this
     * and remembered something I saw at NorthRow when processing a JSON array of
     * objects containing nodes - using a "Comparator". So, I borrowed the approach
     * and it's much cleaner.
     *
     * @param responseBody JSON string containing image data.
     * @return JsonNode representing the best image or null if an error occurs.
     */
    public JsonNode findBestImage(String responseBody) {
        try {
            // Parse the response body into a JSON tree
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode photosNode = root.path("photos");

            // Check if the photos node is valid
            if (photosNode == null || !photosNode.isArray()) {
                logger.error("Invalid photos node in response body");
                return null;
            }

            // Comparator to determine the best image based on alt text and photographer name length
            Comparator<JsonNode> imageComparator = Comparator
                .comparingInt((JsonNode image) -> image.path("alt").asText().length())
                .thenComparingInt(image -> image.path("photographer").asText().length());

            // Find the best image node using the comparator
            Optional<JsonNode> bestImageNode = StreamSupport.stream(photosNode.spliterator(), false)
                .min(imageComparator);

            return bestImageNode.orElse(null);

        } catch (Exception e) {
            logger.error("Error parsing response: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Constructs a JSON string from the given image node.
     * @param imageNode JsonNode containing image data.
     * @return JSON string representation of the image or null if an error occurs.
     */
    public String constructJsonString(JsonNode imageNode) {
        try {
            if (imageNode == null) {
                logger.error("Image node is null, cannot construct JSON");
                return null;
            }

            // Extract relevant fields from the image node
            String imageUrl = imageNode.path("src").path("original").asText();
            String photographer = imageNode.path("photographer").asText();
            String alt = imageNode.path("alt").asText();

            // Create a new JSON object and populate it with the extracted fields
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("url", imageUrl);
            objectNode.put("photographer", photographer);
            objectNode.put("alt", alt);

            // Convert the JSON object to a string
            return objectMapper.writeValueAsString(objectNode);

        } catch (JsonProcessingException e) {
            logger.error("Error while building JSON structure: {}", e.getMessage(), e);
            return null;
        }
    }
}
