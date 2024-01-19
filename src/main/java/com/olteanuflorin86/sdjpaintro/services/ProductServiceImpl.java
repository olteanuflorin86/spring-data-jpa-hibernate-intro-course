package com.olteanuflorin86.sdjpaintro.services;

import org.springframework.stereotype.Service;

import com.olteanuflorin86.sdjpaintro.domain.Product;
import com.olteanuflorin86.sdjpaintro.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Override
	public Product updateQOH(Long id, Integer quantityOnHand) {
		Product product = productRepository.findById(id).orElseThrow();
		
		product.setQuantityOnHand(quantityOnHand);
		
		
		return productRepository.saveAndFlush(product);
	}

}
