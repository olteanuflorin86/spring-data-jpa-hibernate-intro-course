package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.olteanuflorin86.sdjpaintro.domain.Beer;
import com.olteanuflorin86.sdjpaintro.domain.BeerStyleEnum;

@RepositoryRestResource(path = "beer", collectionResourceRel = "beer")
public interface BeerRepository extends JpaRepository<Beer, UUID> {

	Page<Beer> findAllByBeerName(String beerName, Pageable pageable);
	
	Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);
	
	Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);
	
	Beer findByUpc(String upc);
}
