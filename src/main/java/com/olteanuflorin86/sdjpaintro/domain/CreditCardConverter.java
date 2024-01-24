package com.olteanuflorin86.sdjpaintro.domain;

import com.olteanuflorin86.sdjpaintro.config.SpringContextHelper;
import com.olteanuflorin86.sdjpaintro.services.EncryptionService;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return getEncryptionService().encrypt(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return getEncryptionService().decrypt(dbData);
	}
	
    private EncryptionService getEncryptionService(){
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }

}
