package com.github.dsmiles.bestimage;

import com.github.dsmiles.bestimage.controller.BestImageController;
import com.github.dsmiles.bestimage.repository.BestImageRepository;
import com.github.dsmiles.bestimage.service.BestImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BestImageApplicationTest {

    @Autowired
    private BestImageController bestImageController;;

    @Autowired
    private BestImageService bestImageService;

    @Autowired
    private BestImageRepository bestImageRepository;

    @Test
    void contextLoads() {
        assertNotNull(bestImageController);
        assertNotNull(bestImageService);
        assertNotNull(bestImageRepository);
    }
}
