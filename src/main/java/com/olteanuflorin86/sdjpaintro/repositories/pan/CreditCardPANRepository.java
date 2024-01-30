package com.olteanuflorin86.sdjpaintro.repositories.pan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.pan.CreditCardPAN;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPAN, Long> {

	Optional<CreditCardPAN> findByCreditCardId(Long id);
}
