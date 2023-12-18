package com.olteanuflorin86.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.olteanuflorin86.sdjpaintro.domain.composite.AuthorEmbedded;
import com.olteanuflorin86.sdjpaintro.domain.composite.NameId;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId>{

}
