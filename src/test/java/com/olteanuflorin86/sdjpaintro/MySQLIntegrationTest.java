package com.olteanuflorin86.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.olteanuflorin86.sdjpaintro.repositories.BookRepository;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.olteanuflorin86.sdjpaintro.bootstrap"}) 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

	@Autowired
	BookRepository bookRepository;
	
	@Test
	void testMySQL() {
		long count = bookRepository.count();
		assertThat(count).isEqualTo(2);
	}
}
