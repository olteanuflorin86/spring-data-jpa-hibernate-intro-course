package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
