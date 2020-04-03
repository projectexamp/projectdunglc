package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//        String encodedString = new BCryptPasswordEncoder().encode("admin");
//        String encodedString1 = new BCryptPasswordEncoder().encode("user");
//        System.out.println(encodedString);
//        System.out.println(encodedString1);
        SpringApplication.run(DemoApplication.class, args);
    }

}
