package com.olteanuflorin86.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

}
