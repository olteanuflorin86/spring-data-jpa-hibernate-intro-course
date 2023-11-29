package com.olteanuflorin86.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.domain.Book;
import com.olteanuflorin86.sdjpaintro.repositories.BookRepository;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {
	
	private final BookRepository bookRepository;
	
	public DataInitializer(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		bookRepository.deleteAll();
		
		Book bookDDD = new Book("Domain Driven Design", "123", "O'Reilly");
			
		System.out.println("bookDDD id is " + bookDDD.getId());
			
		Book savedDDD = bookRepository.save(bookDDD);
			
		System.out.println("savedDDD id (after save to the DB) is " + savedDDD.getId());
			
			
		Book bookSIA = new Book("Spring In Action", "234234", "O'Reilly");
			
		System.out.println("bookSIA id is " + bookSIA.getId());
			
		Book savedSIA = bookRepository.save(bookSIA);
			
		System.out.println("savedSIA id (after save to the DB) is " + savedSIA.getId());
		
		bookRepository.findAll().forEach(book -> {
			System.out.println("book id is " + book.getId());
			System.out.println("book title is " + book.getTitle());
		});
		
	}

}
