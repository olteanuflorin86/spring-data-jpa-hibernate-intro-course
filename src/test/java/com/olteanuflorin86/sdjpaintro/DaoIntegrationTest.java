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

import java.util.List;

import com.olteanuflorin86.sdjpaintro.domain.Author;
import com.olteanuflorin86.sdjpaintro.domain.Book;

import net.bytebuddy.utility.RandomString;

import com.olteanuflorin86.sdjpaintro.dao.AuthorDao;
import com.olteanuflorin86.sdjpaintro.dao.AuthorDaoImpl;
import com.olteanuflorin86.sdjpaintro.dao.BookDao;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.olteanuflorin86.sdjpaintro.dao"}) 
//@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTest {
	
    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookDao bookDao;

    @Test
	void testFindByISBN() {
    	Book book = new Book();
    	book.setIsbn("1234" + RandomString.make());
    	book.setTitle("ISBN TEST");
    	
    	Book savedBook = bookDao.saveNewBook(book);
    	
    	Book fetchedBook = bookDao.findByISBN(savedBook.getIsbn());
    	
    	assertThat(book).isNotNull(); 
    	assertThat(fetchedBook).isNotNull();
	}
    
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
	
	    Author author = new Author();
	    author.setId(3L);
	
//	    book.setAuthor(author);
	    book.setAuthorId(1L);
	    Book saved = bookDao.saveNewBook(book);
	
	    assertThat(saved).isNotNull();
	}

	@Test
  	void updateBookTest() {
      	Book book = new Book();
      	book.setIsbn("1234");
      	book.setPublisher("Self");
      	book.setTitle("my book");

      	Author author = new Author();
      	author.setId(3L);

//      	book.setAuthor(author);
      	book.setAuthorId(1L);
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
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        Book deleted = bookDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }
    
    @Test
    void testFindAllBooks() {
        List<Book> books = bookDao.findAll();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBookByTitle() {
        Book book = new Book();
        book.setIsbn("1235" + RandomString.make());
        book.setTitle("TITLETEST2");

        Book saved = bookDao.saveNewBook(book);

        Book fetched = bookDao.findBookByTitle(book.getTitle());
        assertThat(fetched).isNotNull();
        
        fetched = bookDao.findBookByTitle(saved.getTitle());
        assertThat(fetched).isNotNull();

        bookDao.deleteBookById(saved.getId());
    }
    
    @Test
    void testGetBookByTitleCriteria() {
        Book book = bookDao.findBookByTitleCriteria("Clean Code");

        assertThat(book).isNotNull();
    }


    
    @Test
    void testFindAllAuthors() {
    	List<Author> authors = authorDao.findAll();
    	
    	assertThat(authors).isNotNull();
    	assertThat(authors.size()).isGreaterThan(0);
    }
    
    @Test
    void testListAuthorByLastNameLike() {
    	List<Author> authors = authorDao.listAuthorByLastNameLike("Wall");
    	
    	assertThat(authors).isNotNull();
    	assertThat(authors.size()).isGreaterThan(0);
    }

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

//        assertThrows(EmptyResultDataAccessException.class, () -> {
//            Author deleted = authorDao.getById(saved.getId());
//        });
        Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();

        assertThat(authorDao.getById(saved.getId()));
    }
    
    @Test
    void testFindAuthorByNameCriteria() {
    	Author author = authorDao.findAuthorByNameCriteria("Craig", "Walls");
    	
    	assertThat(author).isNotNull();
    }
}
