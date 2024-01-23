package com.olteanuflorin86.sdjpaintro.listeners;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.services.EncryptionService;

@Component
public class PreUpdateListener extends AbstractEncryptionListener implements PreUpdateEventListener {

	public PreUpdateListener(EncryptionService encryptionService) {
		super(encryptionService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		System.out.println("In Pre Update");
		
		this.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
		
		return false;
	}

}
