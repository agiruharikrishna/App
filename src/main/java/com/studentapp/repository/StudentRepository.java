package com.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.studentapp.model.Student;

import java.util.Optional;

@Repository  // Explicitly marking this as a Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByName(String name); // Ensures an optional return type

    boolean existsByName(String name); // Method to check if a student exists
}
