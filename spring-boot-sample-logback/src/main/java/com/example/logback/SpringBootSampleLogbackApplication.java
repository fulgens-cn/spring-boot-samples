package com.example.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * SpringBoot默认使用logback进行日志记录
 *
 * @author fulgens
 */
@SpringBootApplication
public class SpringBootSampleLogbackApplication {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleLogbackApplication.class, args);
    }

    // 日志级别 ERROR > WARN > INFO > DEBUG > TRACE（logback没有FATAL级别）
    @PostConstruct
    public void logSomething() {
        logger.trace("TRACE log message");
        logger.info("INFO log message");
    }

}
