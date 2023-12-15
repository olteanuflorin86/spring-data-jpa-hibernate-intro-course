 package com.olteanuflorin86.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;  
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.domain.Book;
import com.olteanuflorin86.sdjpaintro.domain.AuthorUuid;
import com.olteanuflorin86.sdjpaintro.domain.BookUuid;
import com.olteanuflorin86.sdjpaintro.repositories.AuthorUuidRepository;
import com.olteanuflorin86.sdjpaintro.repositories.BookRepository;
import com.olteanuflorin86.sdjpaintro.repositories.BookUuidRepository;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {
	
	private final BookRepository bookRepository;
	private final AuthorUuidRepository authorUuidRepository;
	private final BookUuidRepository bookUuidRepository;
	
	public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository) {
		this.bookRepository = bookRepository;
		this.authorUuidRepository = authorUuidRepository;
		this.bookUuidRepository = bookUuidRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		bookRepository.deleteAll();
		authorUuidRepository.deleteAll();
		bookUuidRepository.deleteAll();
		
		Book bookDDD = new Book("Domain Driven Design", "123", "O'Reilly", null);
			
		System.out.println("bookDDD id is " + bookDDD.getId());
			
		Book savedDDD = bookRepository.save(bookDDD);
			
		System.out.println("savedDDD id (after save to the DB) is " + savedDDD.getId());
			
			
		Book bookSIA = new Book("Spring In Action", "234234", "O'Reilly", null);
			
		System.out.println("bookSIA id is " + bookSIA.getId());
			
		Book savedSIA = bookRepository.save(bookSIA);
			
		System.out.println("savedSIA id (after save to the DB) is " + savedSIA.getId());
		
		bookRepository.findAll().forEach(book -> {
			System.out.println("book id is " + book.getId());
			System.out.println("book title is " + book.getTitle());
		});
		
		AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("Joe");
        authorUuid.setLastName("Buck");
        AuthorUuid savedAuthor = authorUuidRepository.save(authorUuid);
        System.out.println("Saved Author UUID: " + savedAuthor.getId() );
        
        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("All About UUIDs");
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.println("Saved Book UUID: " + savedBookUuid.getId() );
		
	}

}
