package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.Customer;
import com.olteanuflorin86.sdjpaintro.domain.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

	List<OrderHeader> findAllByCustomer(Customer customer);

}
