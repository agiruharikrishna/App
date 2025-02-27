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
        student.setAttendance(true);
        return studentRepository.save(student);  // Return saved student
    }

    public Student toggleAttendance(Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setAttendance(!student.getAttendance());  // Toggle attendance
                    return studentRepository.save(student);  // Save and return updated student
                })
                .orElse(null);  // Return null if student not found
    }
}
