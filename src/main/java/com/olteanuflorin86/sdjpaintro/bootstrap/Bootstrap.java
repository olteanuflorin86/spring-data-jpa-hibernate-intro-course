package com.olteanuflorin86.sdjpaintro.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.olteanuflorin86.sdjpaintro.repositories.OrderHeaderRepository;
import com.olteanuflorin86.sdjpaintro.repositories.CustomerRepository;
import com.olteanuflorin86.sdjpaintro.domain.OrderHeader;
import com.olteanuflorin86.sdjpaintro.domain.Customer;

@Component
public class Bootstrap implements CommandLineRunner {
	
	@Autowired
	BootstrapOrderService bootstrapOrderService;
	
	@Autowired
	CustomerRepository customerRepository;

//	@Autowired
//	OrderHeaderRepository orderHeaderRepository;
//
//	@Transactional
//	void readOrderData() {
////		System.out.println("I was called!");
//		OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();
//		
//		orderHeader.getOrderLines().forEach(ol -> {
//			System.out.println(ol.getProduct().getDescription());
//			
//			ol.getProduct().getCategories().forEach(cat -> {
//				System.out.println(cat.getDescription());
//			});
//		});		
//	}
	
//	@Transactional
	@Override
	public void run(String... args) throws Exception {
//		readOrderData();
		bootstrapOrderService.readOrderData();
		
        Customer customer = new Customer();
        customer.setCustomerName("Testing Version");
        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("Version is: " + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer);
        System.out.println("Version is: " + savedCustomer2.getVersion());
        
        savedCustomer2.setCustomerName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2);
        System.out.println("Version is: " + savedCustomer3.getVersion());

//        customerRepository.deleteById(savedCustomer.getId());
        customerRepository.delete(savedCustomer3);

	}

}
