package com.weecoding.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot启动类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-04  18:10
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.weecoding"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
