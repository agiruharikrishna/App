package com.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.studentapp.model")  // Ensure the package where entities are located
@EnableJpaRepositories(basePackages = "com.studentapp.repository")  // Ensure the repository package is correctly scanned
public class StudentAttendanceApp {
    public static void main(String[] args) {
        // Starts the Spring Boot application
        SpringApplication.run(StudentAttendanceApp.class, args);
        
        // Debugging info (optional)
        System.out.println("🎉 Student Attendance App is running...");
    }
}
