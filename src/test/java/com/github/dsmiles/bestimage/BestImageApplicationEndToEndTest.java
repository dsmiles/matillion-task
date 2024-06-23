package com.github.dsmiles.bestimage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BestImageApplicationEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBestImage_EndPoint_ReturnsResponse() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/best-image"))
            .andExpect(status().isOk())
            .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertNotNull(responseBody);

        // Basic check to see if JSON fields are present not their actual values
        assertTrue(responseBody.contains("url"));
        assertTrue(responseBody.contains("photographer"));
        assertTrue(responseBody.contains("alt"));
    }
}