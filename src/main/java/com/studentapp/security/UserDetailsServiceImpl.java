package com.studentapp.security;

import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;

    // Constructor without PasswordEncoder
    public UserDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found: " + username));

        // Password is already assumed to be hashed in the database
        return User.withUsername(student.getName())
                   .password(student.getPassword()) // Already hashed
                   .roles("STUDENT") // Default role, can be extended to use dynamic roles
                   .build();
    }

    // Register a student without encoding the password (not recommended)
    public Student registerStudent(String name, String password) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(password); // No password encoding
        return studentRepository.save(student); // Save and return the student
    }
}
