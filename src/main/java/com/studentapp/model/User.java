package com.studentapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strateg =  GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    // No-argument constructor (required by JPA)
    public User() {
    }

    // Constructor with arguments (this is the one you need)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
