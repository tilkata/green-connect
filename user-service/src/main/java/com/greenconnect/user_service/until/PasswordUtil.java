package com.greenconnect.user_service.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final String SHA_256 = "SHA-256";

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(SHA_256);
            byte[] salt = generateSalt();
            digest.update(salt);
            byte[] hashedPassword = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
