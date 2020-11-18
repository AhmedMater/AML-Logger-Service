package com.giza.libraries.testSamples;

import com.am.libraries.logger.configuration.EnableLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLogging
public class LibrariesTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibrariesTestApplication.class, args);
    }
}