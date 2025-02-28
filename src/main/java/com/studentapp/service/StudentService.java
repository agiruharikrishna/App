package com.studentapp.service;

import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(String name) {
        Student student = new Student();
        student.setName(name);
        student.setAttendance(false); // Default attendance is false
        return studentRepository.save(student);
    }

    public Student toggleAttendance(Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setAttendance(!student.isAttendance());  // Toggle attendance
                    return studentRepository.save(student);
                })
                .orElse(null);
    }

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*";
    private static final String DIGITS = "0123456789";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String PASSWORD_CHARACTERS = ALPHABET + DIGITS + SPECIAL_CHARACTERS;

    public String generatePassword(String studentName) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least 3 characters from the student's name
        if (studentName.length() >= 3) {
            password.append(studentName.substring(0, 3));
        } else {
            password.append(studentName);
        }

        // Append random characters to make it 10 characters long
        for (int i = password.length(); i < 10; i++) {
            password.append(PASSWORD_CHARACTERS.charAt(random.nextInt(PASSWORD_CHARACTERS.length())));
        }

        return password.toString();
    }
}
