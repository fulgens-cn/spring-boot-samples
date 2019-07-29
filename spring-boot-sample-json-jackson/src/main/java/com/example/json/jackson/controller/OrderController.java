package com.example.json.jackson.controller;

import com.example.json.jackson.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("{id}")
    public Order getById(@PathVariable String id) {
        Order order = Order.builder()
                .id(id)
                .createdTime(new Date())
                .payTime(LocalDateTime.now())
                .build();
        log.info("get order: {}", order);
        return order;
    }

    @PostMapping("")
    public Order createOrder(@RequestBody Order order) {
        log.info("order: {}", order);
        return Order.builder()
                .id(UUID.randomUUID().toString())
                .createdTime(order.getCreatedTime())
                .payTime(order.getPayTime())
                .build();
    }
}
