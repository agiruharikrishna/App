package com.studentapp.service;

import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String name, String password) {
        Optional<Student> student = studentRepository.findByName(name);

        if (student.isPresent()) {
            boolean matches = passwordEncoder.matches(password, student.get().getPassword());
            if (!matches) {
                System.out.println("❌ Authentication failed for user: " + name);
            }
            return matches;
        }

        System.out.println("❌ User not found: " + name);
        return false;
    }

    public Student registerStudent(String name, String rawPassword) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        // Check if student already exists
        if (studentRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("User with name '" + name + "' already exists.");
        }

        Student student = new Student();
        student.setName(name);
        student.setPassword(passwordEncoder.encode(rawPassword));
        student.setEnabled(true);
        return studentRepository.save(student);
    }
}
