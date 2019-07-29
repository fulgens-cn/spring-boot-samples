package com.example.undertow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class UndertowApplication {

    @GetMapping("/")
    public String hello() {
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(UndertowApplication.class, args);
    }
}
