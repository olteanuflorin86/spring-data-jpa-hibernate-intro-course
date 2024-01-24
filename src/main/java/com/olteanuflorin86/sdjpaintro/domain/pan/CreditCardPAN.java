package com.olteanuflorin86.sdjpaintro.domain.pan;

import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.olteanuflorin86.sdjpaintro.domain.CreditCardConverter;

public class CreditCardPAN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String creditCardNumber;
    
}