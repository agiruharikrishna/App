package com.studentapp.service;

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

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String name, String password) {
        Optional<Student> student = studentRepository.findByName(name);
        return student.isPresent() && passwordEncoder.matches(password, student.get().getPassword());
    }

    public Student registerStudent(String name, String rawPassword) {
        if (studentRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("User with name '" + name + "' already exists.");
        }

        Student student = new Student();
        student.setName(name);
        student.setPassword(passwordEncoder.encode(rawPassword));
        student.setStatus(Student.Status.ACTIVE);
        return studentRepository.save(student);
    }

    // ✅ Added this method
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ✅ Added this method
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
