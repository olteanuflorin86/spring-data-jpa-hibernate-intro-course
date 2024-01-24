package com.olteanuflorin86.sdjpaintro.domain.creditcard;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.olteanuflorin86.sdjpaintro.domain.CreditCardConverter;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String cvv;

    @Convert(converter = CreditCardConverter.class)
    private String expirationDate;

}
