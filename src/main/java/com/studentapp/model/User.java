package com.studentapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String name;
    private String password;

    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Getters and setters
}
