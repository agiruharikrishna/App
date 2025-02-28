package com.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.studentapp.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByName(String name);
}

