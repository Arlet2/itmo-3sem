package com.arlet.lab4.services;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class PasswordHashCreator {
    private final MessageDigest messageDigest;

    public PasswordHashCreator() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance("SHA-256");
    }

    public synchronized String createHash(String password, String salt) {
        String pepper = "#13A$P";
        password = pepper +password+salt;

        messageDigest.update(password.getBytes());

        return new String(messageDigest.digest());
    }

    public String generateSalt() {
        byte[] bytes = new byte[10];

        new Random().nextBytes(bytes);

        return new String(bytes);
    }
}
