package com.olteanuflorin86.sdjpaintro.dao;

import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.domain.Author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@Component
public class AuthorDaoImpl implements AuthorDao {
	
	private final EntityManagerFactory emf;
	
	public AuthorDaoImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
    @Override
    public Author getById(Long id) {
        return getEntityManager().find(Author.class, id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }

    private EntityManager getEntityManager() {
    	return emf.createEntityManager();
    }
}
