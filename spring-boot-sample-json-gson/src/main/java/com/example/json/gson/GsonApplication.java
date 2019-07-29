package com.example.json.gson;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@SpringBootApplication
public class GsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsonApplication.class, args);
    }

    @Autowired
    private Gson gson;

    @GetMapping("test")
    public Order test() {
        return Order.builder().id(UUID.randomUUID().toString()).payTime(new Date()).build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class Order {

        private String id;

        private Date payTime;
    }
}
