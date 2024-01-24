package com.olteanuflorin86.sdjpaintro.repositories.creditcard;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.creditcard.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
