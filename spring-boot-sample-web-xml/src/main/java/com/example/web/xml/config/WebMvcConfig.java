package com.example.web.xml.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("xml", MediaType.APPLICATION_XML);

        configurer.favorParameter(true)
                .parameterName("format")
                .favorPathExtension(false)
                .ignoreAcceptHeader(true)
                .ignoreUnknownPathExtensions(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaTypes(mediaTypes);
    }
}
