package com.studentapp.controller;

import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestParam String name, @RequestParam String password) {
        try {
            // Register student and return the created student
            Student student = studentService.registerStudent(name, password);
            return ResponseEntity.ok(student);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Return error message if name already exists
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticate(@RequestParam String name, @RequestParam String password) {
        // Check authentication and return boolean
        boolean isAuthenticated = studentService.authenticate(name, password);
        return ResponseEntity.ok(isAuthenticated);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        // Return list of all students
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/toggleAttendance/{id}")
    public ResponseEntity<?> toggleAttendance(@PathVariable Long id) {
        try {
            // Toggle attendance for the student and return the result
            boolean toggled = studentService.toggleAttendance(id);
            if (toggled) {
                return ResponseEntity.ok("Attendance toggled successfully");
            } else {
                return ResponseEntity.status(404).body("Student not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Handle error if student not found
        }
    }
}
