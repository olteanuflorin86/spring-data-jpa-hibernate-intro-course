package com.olteanuflorin86.sdjpaintro.services;

public interface EncryptionService {

    String encrypt(String freeText);

    String decrypt(String encryptedText);
}
