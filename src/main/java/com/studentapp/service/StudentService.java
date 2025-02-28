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
        return student.isPresent() && passwordEncoder.matches(password, student.get().getPassword());
    }

    public Student registerStudent(String name, String rawPassword) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(passwordEncoder.encode(rawPassword));
        student.setEnabled(true);
        return studentRepository.save(student);
    }
}
