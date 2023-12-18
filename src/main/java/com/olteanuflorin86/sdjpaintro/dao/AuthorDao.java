package com.olteanuflorin86.sdjpaintro.dao;

import com.olteanuflorin86.sdjpaintro.domain.Author;

public interface AuthorDao {

	Author getById(Long id);
	
	Author findAuthorByName(String firstName, String lastName);
}
