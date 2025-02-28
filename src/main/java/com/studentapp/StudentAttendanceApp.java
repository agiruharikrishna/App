package com.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentAttendanceApp {
    public static void main(String[] args) {
        // ✅ Starts the Spring Boot application
        SpringApplication.run(StudentAttendanceApp.class, args);
        
        // ✅ Debugging info (optional)
        System.out.println("🎉 Student Attendance App is running...");
    }
}
