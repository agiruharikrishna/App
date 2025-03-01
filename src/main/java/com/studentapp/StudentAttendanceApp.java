package com.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.studentapp.model")  // Adjust the package name where your JPA entities are located
public class StudentAttendanceApp {
    public static void main(String[] args) {
        // Starts the Spring Boot application
        SpringApplication.run(StudentAttendanceApp.class, args);
        
        // Debugging info (optional)
        System.out.println("🎉 Student Attendance App is running...");
    }
}
