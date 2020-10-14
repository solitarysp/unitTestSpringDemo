package com.lethanh98.unitestDemoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApp {
    public static final String WORKING_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        System.setProperty("app.home", WORKING_DIR);
        SpringApplication.run(MainApp.class, args);
    }
}
