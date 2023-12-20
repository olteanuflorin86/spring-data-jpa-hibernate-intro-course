package com.olteanuflorin86.sdjpaintro.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.olteanuflorin86.sdjpaintro.domain.Book;

public class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getLong(1));
		book.setIsbn(rs.getString(2));
		book.setPublisher(rs.getString(3));
		book.setTitle(rs.getString(4));
		book.setAuthorId(rs.getLong(5));
		
		return book;
	}
	
}
