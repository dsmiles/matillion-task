package com.github.dsmiles.bestimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BestImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestImageApplication.class, args);
    }

}
