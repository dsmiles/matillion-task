package com.github.dsmiles.bestimage.service;
//package codinpad.service;

import org.springframework.stereotype.Service;

//import codinpad.repository.BestImageRepository;
import com.github.dsmiles.bestimage.repository.BestImageRepository;

@Service
public class BestImageService {

    private BestImageRepository bestImageRepository;

    public BestImageService(BestImageRepository bestImageRepository) {
        this.bestImageRepository = bestImageRepository;
    }

    public String find() {
        return bestImageRepository.find();
    }
}
