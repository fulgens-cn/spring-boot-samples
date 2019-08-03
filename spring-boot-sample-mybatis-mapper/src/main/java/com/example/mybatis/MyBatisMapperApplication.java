package com.example.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mybatis.mapper")
public class MyBatisMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisMapperApplication.class, args);
    }
}
