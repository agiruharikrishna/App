package com.studentapp.service;

import com.studentapp.security.UserDetailsServiceImpl;
import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;  // Using UserDetailsServiceImpl

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;  // Injecting UserDetailsServiceImpl
    }

    // Authentication logic
    public boolean authenticate(String name, String password) {
        Optional<Student> student = studentRepository.findByName(name);
        return student.isPresent() && passwordEncoder.matches(password, student.get().getPassword());
    }

    // Updated registration method leveraging UserDetailsServiceImpl
    public Student registerStudent(String name, String rawPassword) {
        // Call to UserDetailsServiceImpl for registration and return the student
        return userDetailsService.registerStudent(name, rawPassword);  // Ensure this returns Student
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
