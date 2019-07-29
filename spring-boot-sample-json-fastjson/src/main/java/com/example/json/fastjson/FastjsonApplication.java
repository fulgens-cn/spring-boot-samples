package com.example.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@SpringBootApplication
public class FastjsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastjsonApplication.class, args);
    }

    @GetMapping("test")
    public Order test() {
        return Order.builder().id(UUID.randomUUID().toString()).hasPaid(true).build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class Order {

        private String id;

        @JSONField(serialize = false)
        private boolean hasPaid;
    }
}
