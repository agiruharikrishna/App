package com.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.studentapp.model") // Scans the package where your entity classes are located
@EnableJpaRepositories(basePackages = "com.studentapp.repository") // Optional if needed for repository scanning
public class StudentAttendanceApp {
    public static void main(String[] args) {
        SpringApplication.run(StudentAttendanceApp.class, args);
        System.out.println("🎉 Student Attendance App is running...");
    }
}
