package com.example.web.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 需要继承{@link SpringBootServletInitializer}打成war包部署才不会报错
 *
 * @author fulgens
 */
@SpringBootApplication
public class WebJspApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebJspApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebJspApplication.class, args);
    }
}
