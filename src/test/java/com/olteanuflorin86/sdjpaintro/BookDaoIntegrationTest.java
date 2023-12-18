package com.olteanuflorin86.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.olteanuflorin86.sdjpaintro.dao.BookDao;
import com.olteanuflorin86.sdjpaintro.domain.Book;
import com.olteanuflorin86.sdjpaintro.domain.Author;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.olteanuflorin86.sdjpaintro.dao"})
//@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {
	
    @Autowired
    BookDao bookDao;
    
    @Test
    void testGetBook() {
        Book book = bookDao.getById(3L);

        assertThat(book.getId()).isNotNull();
    }
    
    @Test
    void testGetBookByName() {
        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
    }
    
    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
//        book.setAuthorId(3L);
        Author author = new Author();
        author.setId(3L);

        book.setAuthor(author);
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }
    
    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
//        book.setAuthorId(3L);
        Author author = new Author();
        author.setId(3L);

        book.setAuthor(author);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }
    
    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
//        book.setAuthorId(3L);
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        Book deleted = bookDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }

}
