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
                   .password(student.getPassword()) // Already hashed!
                   .roles("STUDENT") // Optional: Assign a default role
                   .build();
    }

    // Method to register user with encoded password
    public void registerStudent(String name, String password) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(passwordEncoder.encode(password)); // Encode the password
        studentRepository.save(student);
    }
}
