package com.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Spring Boot application for Chess Player
 * This enables deployment to Heroku and other cloud platforms
 */
@SpringBootApplication
@ServletComponentScan
public class ChessApplication {
    
    public static void main(String[] args) {
        System.out.println("Starting Chess Player Application...");
        SpringApplication.run(ChessApplication.class, args);
    }
}
