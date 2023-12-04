package com.softuni.fitlaunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FitLaunchApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitLaunchApplication.class, args);
    }

}
