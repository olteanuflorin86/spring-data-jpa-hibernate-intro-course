package com.olteanuflorin86.sdjpaintro.domain;

import com.olteanuflorin86.sdjpaintro.config.SpringContextHelper;
import com.olteanuflorin86.sdjpaintro.services.EncryptionService;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CreditCardJPACallback {

	@PrePersist
	@PreUpdate
	public void beforeInsertOrUpdate(CreditCard creditCard) {
		System.out.println("before update was called...");
		
		creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
	}
	
	@PostPersist
	@PostUpdate
	@PostLoad
	public void postLoad(CreditCard creditCard) {
		System.out.println("Post Load was called...");
		
		creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
	}
	
    private EncryptionService getEncryptionService(){
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
	
}
