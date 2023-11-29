package com.olteanuflorin86.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import com.olteanuflorin86.sdjpaintro.domain.Book;
import com.olteanuflorin86.sdjpaintro.repositories.BookRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.olteanuflorin86.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSplice {

	@Autowired
	BookRepository bookRepository;
	
	@Commit
	@Order(1)
	@Test
	void testJpaTestSplice() {
		long countBefore = bookRepository.count();
		bookRepository.save(new Book("Domain Driven Design 2", "1234", "O'Reilly"));		
		long countAfter = bookRepository.count();
		
		assertThat(countBefore).isEqualTo(2);
		assertThat(countBefore).isLessThan(countAfter);
	}
	
//	@Commit
	@Order(2)
	@Test
	void testJpaTestSpliceTransaction() {
		long count = bookRepository.count();
		assertThat(count).isEqualTo(3);
	}
	
}
