package com.olteanuflorin86.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat; 

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.olteanuflorin86.sdjpaintro.repositories.AuthorCompositeRepository;
import com.olteanuflorin86.sdjpaintro.repositories.AuthorUuidRepository;
import com.olteanuflorin86.sdjpaintro.repositories.BookNaturalRepository;
import com.olteanuflorin86.sdjpaintro.repositories.BookRepository;
import com.olteanuflorin86.sdjpaintro.repositories.BookUuidRepository;
import com.olteanuflorin86.sdjpaintro.domain.AuthorUuid;
import com.olteanuflorin86.sdjpaintro.domain.BookNatural;
import com.olteanuflorin86.sdjpaintro.domain.BookUuid;
import com.olteanuflorin86.sdjpaintro.domain.composite.AuthorComposite;
import com.olteanuflorin86.sdjpaintro.domain.composite.NameId;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.olteanuflorin86.sdjpaintro.bootstrap"}) 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;
	
	@Autowired
	BookNaturalRepository bookNaturalRepository;
	
	@Autowired
	AuthorCompositeRepository authorCompositeRepository;
	
	@Test
	void testMySQL() {
		long count = bookRepository.count();
		assertThat(count).isEqualTo(2);
	}
	
    @Test
    void testBookUuid() {
        BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId());

        BookUuid fetched = bookUuidRepository.getById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorUuid() {
        AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        AuthorUuid fetched = authorUuidRepository.getById(authorUuid.getId());
        assertThat(fetched).isNotNull();
    }

	@Test
	void bookNaturalTest() {
		BookNatural bookNatural = new BookNatural();
		bookNatural.setTitle("My Book");
		BookNatural savedBookNatural = bookNaturalRepository.save(bookNatural);
		
		BookNatural fetchedBookNatural = bookNaturalRepository.getById(savedBookNatural.getTitle());
		assertThat(fetchedBookNatural).isNotNull();
	}
	
	@Test
	void authorCompositeTest() {
		NameId nameId = new NameId("Florin", "Mylastname");
		AuthorComposite authorComposite = new AuthorComposite();
		authorComposite.setFirstName(nameId.getFirstName());
		authorComposite.setLastName(nameId.getLastName());
		authorComposite.setCountry("Mycountry");
		
		AuthorComposite savedAuthorComposite = authorCompositeRepository.save(authorComposite);
		assertThat(savedAuthorComposite).isNotNull();
	
		AuthorComposite fetchedAuthorComposite = authorCompositeRepository.getById(nameId);
		assertThat(fetchedAuthorComposite).isNotNull();
	}
}
