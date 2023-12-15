package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.BookUuid;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {

}
