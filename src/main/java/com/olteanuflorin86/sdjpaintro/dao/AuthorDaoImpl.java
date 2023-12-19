package com.olteanuflorin86.sdjpaintro.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.domain.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {

	@Override
	public Author getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author saveNewAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author updateAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAuthorById(Long id) {
		// TODO Auto-generated method stub
		
	}

	private RowMapper<Author> getRowMapper() {
		return new AuthorMapper();
	}
}
