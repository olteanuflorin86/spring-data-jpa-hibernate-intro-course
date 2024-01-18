package com.olteanuflorin86.sdjpaintro.bootstrap;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.olteanuflorin86.sdjpaintro.repositories.OrderHeaderRepository;
import com.olteanuflorin86.sdjpaintro.domain.OrderHeader;

@Component
public class Bootstrap implements CommandLineRunner {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;
	
	@Transactional
	void readOrderData() {
//		System.out.println("I was called!");
		OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();
		
		orderHeader.getOrderLines().forEach(ol -> {
			System.out.println(ol.getProduct().getDescription());
			
			ol.getProduct().getCategories().forEach(cat -> {
				System.out.println(cat.getDescription());
			});
		});		
	}
	
//	@Transactional
	@Override
	public void run(String... args) throws Exception {
		readOrderData();
	}

}
