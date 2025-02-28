package com.studentapp.controller;

import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Changed to @RestController for API responses
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        studentService.addStudent(student.getName());
        return ResponseEntity.ok("Student added successfully");
    }

    @PutMapping("/updateAttendance/{id}")
    public ResponseEntity<Boolean> updateAttendance(@PathVariable Long id) {
        boolean isUpdated = studentService.toggleAttendance(id);
        return ResponseEntity.ok(isUpdated);
    }

    @GetMapping("/generate-password")
    public ResponseEntity<String> generatePassword(@RequestParam String studentName) {
        String password = studentService.generatePassword(studentName);
        return ResponseEntity.ok(password);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login page");
    }
}
