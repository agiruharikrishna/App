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
        student.setAttendance(false);
        return studentRepository.save(student);
    }

    public Student toggleAttendance(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setAttendance(!student.getAttendance());
            return studentRepository.save(student);
        }
        return null;  // Handle case where student is not found
    }
}
