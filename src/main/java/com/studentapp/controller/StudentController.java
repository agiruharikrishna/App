package com.studentapp.controller;

import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Student name cannot be empty");
        }
        studentService.addStudent(student.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }

    @PutMapping("/updateAttendance/{id}")
    public ResponseEntity<String> updateAttendance(@PathVariable Long id) {
        boolean isUpdated = studentService.toggleAttendance(id);
        if (isUpdated) {
            return ResponseEntity.ok("Attendance updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

    @GetMapping("/generate-password")
    public ResponseEntity<String> generatePassword(@RequestParam String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Student name is required");
        }
        String password = studentService.generatePassword(studentName);
        return ResponseEntity.ok(password);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login page");
    }
}
