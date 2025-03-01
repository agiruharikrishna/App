package com.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.studentapp.repository")
public class StudentAttendanceApp {

    public static void main(String[] args) {
        SpringApplication.run(StudentAttendanceApp.class, args);
    }
}
