package com.olteanuflorin86.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.olteanuflorin86.sdjpaintro.dao.AuthorDao;
import com.olteanuflorin86.sdjpaintro.dao.AuthorDaoImpl;
import com.olteanuflorin86.sdjpaintro.domain.Author;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.olteanuflorin86.sdjpaintro.dao"})
//@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

	@Autowired
	AuthorDao authorDao;
	
	@Test
	void testGetAuthor() {
		
		Author author = authorDao.getById(1L);
		assertThat(author).isNotNull();
	}

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");

        assertThat(author).isNotNull();
    }
    
    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("Florin");
        author.setLastName("F");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
    }
    
    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("florin");
        author.setLastName("f");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("F");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("F");
    }
    
    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("florin");
        author.setLastName("f");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        Author deleted = authorDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }
    
}
