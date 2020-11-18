package com.am.libraries.logger;

import com.am.libraries.logger.configuration.EnableLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLogging
public class DefaultLoggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DefaultLoggerApplication.class, args);
    }
}