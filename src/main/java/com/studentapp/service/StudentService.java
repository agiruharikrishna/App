package com.studentapp.service;

import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import com.studentapp.security.UserDetailsServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserDetailsServiceImpl userDetailsService;  // Using UserDetailsServiceImpl

    public StudentService(StudentRepository studentRepository, UserDetailsServiceImpl userDetailsService) {
        this.studentRepository = studentRepository;
        this.userDetailsService = userDetailsService;  // Injecting UserDetailsServiceImpl
    }

    // Authentication logic (without password encoding)
    public boolean authenticate(String name, String password) {
        Optional<Student> student = studentRepository.findByName(name);
        return student.isPresent() && student.get().getPassword().equals(password);  // No encoding comparison
    }

    // Updated registration method leveraging UserDetailsServiceImpl
    public Student registerStudent(String name, String password) {
        return userDetailsService.registerStudent(name, password);  // Make sure this returns Student
    }

    // Method to fetch all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Toggle attendance status
    public boolean toggleAttendance(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setStatus(student.getStatus() == Student.Status.ACTIVE ? Student.Status.INACTIVE : Student.Status.ACTIVE);
            studentRepository.save(student);
            return true;
        } else {
            throw new IllegalArgumentException("Student not found with ID: " + id);
        }
    }
}
