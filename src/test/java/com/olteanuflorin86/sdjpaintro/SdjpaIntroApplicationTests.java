package com.olteanuflorin86.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.olteanuflorin86.sdjpaintro.repositories.BookRepository;

@Disabled // no h2 context in project
@SpringBootTest
class SdjpaIntroApplicationTests {
	
	@Autowired
	BookRepository bookRepository; 

	@Test
	void testBookRepository() {
		long count = bookRepository.count();
	
		assertThat(count).isGreaterThan(0);
	}
	
	@Test
	void contextLoads() {
	}

}
