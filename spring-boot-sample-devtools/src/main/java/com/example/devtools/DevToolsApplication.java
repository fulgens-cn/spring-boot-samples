package com.example.devtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DevToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevToolsApplication.class, args);
    }
}
