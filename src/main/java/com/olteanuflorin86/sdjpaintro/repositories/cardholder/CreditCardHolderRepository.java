package com.olteanuflorin86.sdjpaintro.repositories.cardholder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.cardholder.CreditCardHolder;

public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {

}
