package com.zhuky.rdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class RdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RdbApplication.class, args);

    }

}
