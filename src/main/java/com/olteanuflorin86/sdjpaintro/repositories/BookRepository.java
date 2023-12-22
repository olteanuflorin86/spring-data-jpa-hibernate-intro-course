package com.olteanuflorin86.sdjpaintro.repositories;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olteanuflorin86.sdjpaintro.domain.Book;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findBookByTitle(String title);
	
	Book readByTitle(String title);
	
	@Nullable
	Book getByTitle(@Nullable String title);
	
	Stream<Book> findAllByTitleNotNull();
	
	@Async
	Future<Book> queryByTitle(String title);

	@Query("SELECT b FROM Book b WHERE b.title = ?1")
	Book findBookByTitleWithQuery(String title);
}
