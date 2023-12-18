package com.olteanuflorin86.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.composite.AuthorComposite;
import com.olteanuflorin86.sdjpaintro.domain.composite.NameId;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId>{

}
