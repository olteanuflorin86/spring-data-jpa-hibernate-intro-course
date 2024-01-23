package com.olteanuflorin86.sdjpaintro.listeners;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.services.EncryptionService;

@Component
public class PreInsertListener extends AbstractEncryptionListener implements PreInsertEventListener {

	public PreInsertListener(EncryptionService encryptionService) {
		super(encryptionService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		System.out.println("In Pre Insert");
		
		this.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
		
		return false;
	}

}
