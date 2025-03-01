package com.studentapp.security;

import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor with PasswordEncoder injected without @Lazy (if circular dependency isn't an issue)
    public UserDetailsServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found: " + username));

        // Password is already hashed in the database, no need for {noop}
        return User.withUsername(student.getName())
                   .password(student.getPassword()) // Already hashed
                   .roles("STUDENT") // Default role, can be extended to use dynamic roles
                   .build();
    }

    // Register a student with encoded password
    public Student registerStudent(String name, String password) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(passwordEncoder.encode(password)); // Encode the password
        return studentRepository.save(student); // Save and return the student
    }
}
