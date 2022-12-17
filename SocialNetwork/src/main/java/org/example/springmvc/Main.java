package org.example.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.exmaple.security.client")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
