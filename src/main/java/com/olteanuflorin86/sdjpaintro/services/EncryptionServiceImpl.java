package com.olteanuflorin86.sdjpaintro.services;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

	@Override
	public String encrypt(String freeText) {
		return Base64.getEncoder().encodeToString(freeText.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public String decrypt(String encryptedText) {
		return new String(Base64.getDecoder().decode(encryptedText));
	}

}
