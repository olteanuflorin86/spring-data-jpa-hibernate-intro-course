package com.olteanuflorin86.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat; 

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.olteanuflorin86.sdjpaintro.repositories.BookNaturalRepository;
import com.olteanuflorin86.sdjpaintro.repositories.BookRepository;

import com.olteanuflorin86.sdjpaintro.domain.BookNatural;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.olteanuflorin86.sdjpaintro.bootstrap"}) 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookNaturalRepository bookNaturalRepository;
	
	@Test
	void testMySQL() {
		long count = bookRepository.count();
		assertThat(count).isEqualTo(2);
	}
	
	@Test
	void bookNaturalTest() {
		BookNatural bookNatural = new BookNatural();
		bookNatural.setTitle("My Book");
		BookNatural savedBookNatural = bookNaturalRepository.save(bookNatural);
		
		BookNatural fetchedBookNatural = bookNaturalRepository.getById(savedBookNatural.getTitle());
		assertThat(fetchedBookNatural).isNotNull();
	}
}
