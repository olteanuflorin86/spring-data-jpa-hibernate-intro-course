package com.olteanuflorin86.sdjpaintro.domain;

import com.olteanuflorin86.sdjpaintro.interceptors.EncryptedString;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
//@EntityListeners(CreditCardJPACallback.class)
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@EncryptedString
	@Convert(converter = CreditCardConverter.class)
	private String creditCardNumber;
	
	private String cvv;
	
	private String expirationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@PrePersist
	public void prePersistCallback() {
		System.out.println("JPA PrePresist Callback was called");
	}
}
