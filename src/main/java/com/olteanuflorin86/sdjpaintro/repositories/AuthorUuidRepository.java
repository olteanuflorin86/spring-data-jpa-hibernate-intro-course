package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.AuthorUuid;

public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {

}
