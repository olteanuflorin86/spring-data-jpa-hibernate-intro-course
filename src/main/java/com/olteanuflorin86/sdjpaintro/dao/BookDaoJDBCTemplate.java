package com.olteanuflorin86.sdjpaintro.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import com.olteanuflorin86.sdjpaintro.domain.Book;

public class BookDaoJDBCTemplate implements BookDao {
	
    private final JdbcTemplate jdbcTemplate;

    public BookDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public Book getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM book where id = ?", getBookMapper(), id);
	}

	@Override
	public Book findBookByTitle(String title) {
		return jdbcTemplate.queryForObject("SELECT * FROM book where title = ?", getBookMapper(), title);
	}

	@Override
	public Book saveNewBook(Book book) {
        jdbcTemplate.update("INSERT INTO book (isbn, publisher, title, author_id) VALUES (?, ?, ?, ?)",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId());

        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.getById(createdId);
	}

	@Override
	public Book updateBook(Book book) {
        jdbcTemplate.update("UPDATE book set isbn = ?, publisher = ?, title = ?, author_id = ? where id = ?",
                book.getIsbn(), book.getPublisher(), book.getTitle(), book.getAuthorId(), book.getId());

        return this.getById(book.getId());
	}

	@Override
	public void deleteBookById(Long id) {
		jdbcTemplate.update("DELETE from book where id = ?", id);
		
	}
	
	@Override
	public List<Book> findAllBooks() {
		return jdbcTemplate.query("SELECT * FROM book", getBookMapper());
	}
	
	@Override
	public List<Book> findAllBooks(int pageSize, int offset) {
		return jdbcTemplate.query("SELECT * FROM book limit ? offset ?", getBookMapper(), pageSize, offset);
	}
	
    private BookMapper getBookMapper(){
        return new BookMapper();
    }

	@Override
	public List<Book> findAllBooks(Pageable pageable) {
		return jdbcTemplate.query("SELECT * FROM book limit ? offset ?", getBookMapper(), pageable.getPageSize(), pageable.getOffset());
	}


}