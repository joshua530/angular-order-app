package com.foods.angularwebapp.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class Encryption {
  public String generateSecretKey(int numBits) {
    try {
      return convertSecretKeyToString(generateKey(numBits));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Failed to generate random key, reason: " + e.getMessage(), e);
    }
  }

  public SecretKey generateKey(int length) throws NoSuchAlgorithmException {
    KeyGenerator generator = KeyGenerator.getInstance("AES");
    generator.init(length);
    SecretKey originalKey = generator.generateKey();
    return originalKey;
  }

  public SecretKey getKeyFromPassword(String password, String salt) {
    try {
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 80000, 256);
      SecretKey originalKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
      return originalKey;
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Server error occured", e);
    } catch (InvalidKeySpecException e) {
      throw new RuntimeException("Server error occured", e);
    } catch (NullPointerException e) {
      throw new RuntimeException("Invalid authentication details provided", e);
    }
  }

  public String encryptPassword(String password, String salt) {
    SecretKey key = getKeyFromPassword(password, salt);
    return convertSecretKeyToString(key);
  }

  public String convertSecretKeyToString(SecretKey key) {
    byte[] rawData = key.getEncoded();
    String encodedKey = Base64.getEncoder().encodeToString(rawData);
    return encodedKey;
  }

  public SecretKey convertStringToSecretKey(String key) {
    byte[] decodedKey = Base64.getDecoder().decode(key);
    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    return originalKey;
  }
}
