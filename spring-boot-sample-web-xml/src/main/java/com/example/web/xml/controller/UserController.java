package com.example.web.xml.controller;

import com.example.web.xml.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping(value = "",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User save(@RequestBody User user) {
        log.info("user: {}", user);
        return User.builder().id(UUID.randomUUID().toString())
                .username(user.getUsername())
                .age(user.getAge())
                .addressList(user.getAddressList())
                .build();
    }
}
