package com.joysistvi.recordingapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Utility Class
// Provides methods for hashing passwords using SHA-256
public class PasswordUtil {
	
	// Hashes a plain text password using SHA-256
	public static String hashPassword(String password) {

        try {

            // Create a SHA-256 MessageDigest object
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Convert the password into a byte array and hash it
            byte[] hashedBytes = messageDigest.digest(password.getBytes());

            // Convert the hashed bytes into a hexadecimal string
            StringBuilder hashedPassword = new StringBuilder();

            for (byte b : hashedBytes) {
                hashedPassword.append(String.format("%02x", b));
            }

            return hashedPassword.toString();

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("Error hashing password.", e);

        }
    }
}
