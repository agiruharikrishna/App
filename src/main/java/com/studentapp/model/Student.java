package com.studentapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private boolean enabled;
    private boolean present; // Added for attendance tracking

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(String name, String password) {
        this.name = name;
        this.password = password;
        this.enabled = true;
        this.present = false; // Default attendance is false
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isPresent() {
        return present;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
