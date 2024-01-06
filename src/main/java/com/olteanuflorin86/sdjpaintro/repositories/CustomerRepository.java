package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);

}
