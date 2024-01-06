package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByDescription(String description);

}
