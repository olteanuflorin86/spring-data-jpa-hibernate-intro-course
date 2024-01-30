package com.olteanuflorin86.sdjpaintro.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olteanuflorin86.sdjpaintro.domain.cardholder.CreditCardHolder;
import com.olteanuflorin86.sdjpaintro.domain.creditcard.CreditCard;
import com.olteanuflorin86.sdjpaintro.domain.pan.CreditCardPAN;
import com.olteanuflorin86.sdjpaintro.repositories.cardholder.CreditCardHolderRepository;
import com.olteanuflorin86.sdjpaintro.repositories.creditcard.CreditCardRepository;
import com.olteanuflorin86.sdjpaintro.repositories.pan.CreditCardPANRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

	private final CreditCardHolderRepository creditCardHolderRepository;
	private final CreditCardRepository creditCardRepository;
	private final CreditCardPANRepository creditCardPANRepository;
	
	@Override
	public CreditCard getCreditCardById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public CreditCard saveCreditCard(CreditCard cc) {
		CreditCard savedCC = creditCardRepository.save(cc);
		savedCC.setFirstName(cc.getFirstName());
        savedCC.setLastName(cc.getLastName());
        savedCC.setZipCode(cc.getCreditCardNumber());
        savedCC.setCreditCardNumber(cc.getCreditCardNumber());
        
        creditCardHolderRepository.save(CreditCardHolder.builder()
                .firstName(savedCC.getFirstName())
                .lastName(savedCC.getLastName())
                .zipCode(savedCC.getZipCode())
                .creditCardId(savedCC.getId())
        .build());
        
        creditCardPANRepository.save(CreditCardPAN.builder()
                .creditCardNumber(savedCC.getCreditCardNumber())
                .creditCardId(savedCC.getId())
        .build());
        
		return savedCC;
	}

}
