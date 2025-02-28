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
    public ResponseEntity<Student> registerStudent(@RequestParam String name, @RequestParam String password) {
        Student student = studentService.registerStudent(name, password);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticate(@RequestParam String name, @RequestParam String password) {
        boolean isAuthenticated = studentService.authenticate(name, password);
        return ResponseEntity.ok(isAuthenticated);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/toggleAttendance/{id}")
    public ResponseEntity<Boolean> toggleAttendance(@PathVariable Long id) {
        boolean toggled = studentService.toggleAttendance(id);
        return ResponseEntity.ok(toggled);
    }
}
