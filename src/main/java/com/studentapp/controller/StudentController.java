package com.studentapp.controller;

import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Changed from @Controller to @RestController for JSON responses
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public List<Student> home() {
        return studentService.getAllStudents();
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestParam String name) {
        return studentService.addStudent(name);
    }

    @PutMapping("/updateAttendance/{id}")
    public Student updateAttendance(@PathVariable Long id) {
        return studentService.toggleAttendance(id);
    }
}
