package com.denghb.simplex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableCaching
public class App {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(App.class, args);
        System.out.println("started:" + (System.currentTimeMillis() - start) / 1000.0 + "s");
    }
}
