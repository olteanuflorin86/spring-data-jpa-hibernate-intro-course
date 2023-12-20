package com.olteanuflorin86.sdjpaintro.dao;

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.domain.Author;

import org.springframework.jdbc.core.RowMapper;

@Component
public class AuthorDaoImpl implements AuthorDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Author getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM author where id = ?", getRowMapper(), id);
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		return jdbcTemplate.queryForObject("SELECT * FROM author WHERE first_name = ? AND last_name = ?", getRowMapper(), firstName, lastName);
	}

	@Override
	public Author saveNewAuthor(Author author) {
		jdbcTemplate.update("INSERT INTO author (first_name, last_name) VALUES (?, ?)", author.getFirstName(), author.getLastName());

		Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
		return this.getById(createdId);
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
