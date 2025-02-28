package com.studentapp.service;

import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        // Check if student already exists
        if (studentRepository.findByName(studentName).isPresent()) {
            throw new IllegalArgumentException("Student with name '" + studentName + "' already exists.");
        }

        String generatedPassword = generatePassword(studentName);
        Student student = new Student(studentName, passwordEncoder.encode(generatedPassword));
        studentRepository.save(student);
    }

    public boolean toggleAttendance(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setPresent(!student.isPresent()); // Toggle attendance
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public String generatePassword(String studentName) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) { // 8-character password
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    public boolean authenticate(String name, String password) {
        Optional<Student> student = studentRepository.findByName(name);
        return student.map(value -> passwordEncoder.matches(password, value.getPassword())).orElse(false);
    }

    public Student registerStudent(String name, String rawPassword) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if (studentRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("User with name '" + name + "' already exists.");
        }

        Student student = new Student(name, passwordEncoder.encode(rawPassword));
        return studentRepository.save(student);
    }
}
