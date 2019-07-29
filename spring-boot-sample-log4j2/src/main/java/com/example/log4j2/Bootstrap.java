package com.example.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Bootstrap {

    private final Logger logger = LogManager.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

    @PostConstruct
    public void logSomething() {
        logger.fatal("FATAL log message");
        logger.error("ERROR log message");
        logger.warn("WARN log message");
        logger.info("INFO log message");
        logger.debug("DEBUG log message");
        logger.trace("TRACE log message");
    }
}
