package com.studentapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    // ✅ Default constructor (required by JPA)
    public Student() {}

    // ✅ Parameterized constructor (used in StudentService)
    public Student(String name, String password, Status status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }

    // Auto-update `updatedAt` before saving
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setStatus(Status status) { this.status = status; }
}
