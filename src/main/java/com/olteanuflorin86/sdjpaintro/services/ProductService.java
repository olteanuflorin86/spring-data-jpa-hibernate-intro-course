package com.olteanuflorin86.sdjpaintro.services;

import com.olteanuflorin86.sdjpaintro.domain.Product;

public interface ProductService {

	Product saveProduct(Product product);
	
	Product updateQOH(Long id, Integer quantityOnHand);
	
}
