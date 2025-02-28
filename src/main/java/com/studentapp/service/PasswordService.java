package com.studentapp.service;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class PasswordService {

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*";
    private static final String DIGITS = "0123456789";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String PASSWORD_CHARACTERS = ALPHABET + DIGITS + SPECIAL_CHARACTERS;

    public String generatePassword(String studentName) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least 3 characters from the student's name
        if (studentName.length() >= 3) {
            password.append(studentName.substring(0, 3));
        } else {
            password.append(studentName);
        }

        // Append random characters to make it 10 characters long
        for (int i = password.length(); i < 10; i++) {
            password.append(PASSWORD_CHARACTERS.charAt(random.nextInt(PASSWORD_CHARACTERS.length())));
        }

        return password.toString();
    }
}
