package com.studentapp.service;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class PasswordService {

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*";
    private static final String DIGITS = "0123456789";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String PASSWORD_CHARACTERS = ALPHABET + DIGITS + SPECIAL_CHARACTERS;

    private static final int PASSWORD_LENGTH = 10;
    private final SecureRandom random = new SecureRandom();

    public String generatePassword(String studentName) {
        StringBuilder password = new StringBuilder();

        // Ensure at least the first 3 characters from studentName (or full name if less than 3)
        int nameLength = Math.min(studentName.length(), 3);
        password.append(studentName.substring(0, nameLength));

        // Ensure at least one digit and one special character
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Fill the rest with random characters to reach PASSWORD_LENGTH
        while (password.length() < PASSWORD_LENGTH) {
            password.append(PASSWORD_CHARACTERS.charAt(random.nextInt(PASSWORD_CHARACTERS.length())));
        }

        // Shuffle the password to randomize character positions
        return shuffleString(password.toString());
    }

    private String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return new String(array);
    }
}
