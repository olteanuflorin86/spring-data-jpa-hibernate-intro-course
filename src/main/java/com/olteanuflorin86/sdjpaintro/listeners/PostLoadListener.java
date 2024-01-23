package com.olteanuflorin86.sdjpaintro.listeners;

import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.services.EncryptionService;

@Component
public class PostLoadListener extends AbstractEncryptionListener implements PostLoadEventListener {

	public PostLoadListener(EncryptionService encryptionService) {
		super(encryptionService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onPostLoad(PostLoadEvent event) {
		System.out.println("In Post Load");
		
		this.decrypt(event.getEntity());
	}

}
