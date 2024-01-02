package com.olteanuflorin86.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
