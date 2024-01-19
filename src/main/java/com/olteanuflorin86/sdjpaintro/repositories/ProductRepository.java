package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.olteanuflorin86.sdjpaintro.domain.Product;

import jakarta.persistence.LockModeType;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByDescription(String description);
	
	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Product> findById(Long id);

}
