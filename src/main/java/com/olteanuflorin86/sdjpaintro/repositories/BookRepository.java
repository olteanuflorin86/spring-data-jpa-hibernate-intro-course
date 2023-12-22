package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olteanuflorin86.sdjpaintro.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findBookByTitle(String title);

}
