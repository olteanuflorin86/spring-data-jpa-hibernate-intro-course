package com.olteanuflorin86.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import com.olteanuflorin86.sdjpaintro.domain.Author;
import com.olteanuflorin86.sdjpaintro.dao.AuthorDao;
import com.olteanuflorin86.sdjpaintro.dao.AuthorDaoImpl;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.olteanuflorin86.sdjpaintro.dao"}) 
//@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTest {
	
    @Autowired
    AuthorDao authorDao;

//    @Autowired
//    BookDao bookDao;
//
//    @Test
//    void testDeleteBook() {
//        Book book = new Book();
//        book.setIsbn("1234");
//        book.setPublisher("Self");
//        book.setTitle("my book");
//        Book saved = bookDao.saveNewBook(book);
//
//        bookDao.deleteBookById(saved.getId());
//
//        Book deleted = bookDao.getById(saved.getId());
//
//        assertThat(deleted).isNull();
//    }
//
//    @Test
//    void updateBookTest() {
//        Book book = new Book();
//        book.setIsbn("1234");
//        book.setPublisher("Self");
//        book.setTitle("my book");
//
//        Author author = new Author();
//        author.setId(3L);
//
//        book.setAuthor(author);
//        Book saved = bookDao.saveNewBook(book);
//
//        saved.setTitle("New Book");
//        bookDao.updateBook(saved);
//
//        Book fetched = bookDao.getById(saved.getId());
//
//        assertThat(fetched.getTitle()).isEqualTo("New Book");
//    }
//
//    @Test
//    void testSaveBook() {
//        Book book = new Book();
//        book.setIsbn("1234");
//        book.setPublisher("Self");
//        book.setTitle("my book");
//
//        Author author = new Author();
//        author.setId(3L);
//
//        book.setAuthor(author);
//        Book saved = bookDao.saveNewBook(book);
//
//        assertThat(saved).isNotNull();
//    }
//
//    @Test
//    void testGetBookByName() {
//        Book book = bookDao.findBookByTitle("Clean Code");
//
//        assertThat(book).isNotNull();
//    }
//
//    @Test
//    void testGetBook() {
//        Book book = bookDao.getById(3L);
//
//        assertThat(book.getId()).isNotNull();
//    }



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
        author.setLastName("Florin2");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("florin");
        author.setLastName("f");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Florin2");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Florin2");
    }
    
    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("florin");
        author.setLastName("f");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            Author deleted = authorDao.getById(saved.getId());
        });
    }
}
