package com.studentapp.repository;

import com.studentapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    // You can add custom queries if needed, e.g., to find a user by name
}
