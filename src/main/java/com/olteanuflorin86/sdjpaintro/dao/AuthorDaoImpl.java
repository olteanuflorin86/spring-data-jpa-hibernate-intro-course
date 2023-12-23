package com.olteanuflorin86.sdjpaintro.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component; 

import com.olteanuflorin86.sdjpaintro.domain.Author;
import com.olteanuflorin86.sdjpaintro.repositories.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Component
public class AuthorDaoImpl implements AuthorDao {
	
	private final AuthorRepository authorRepository;
	
	public AuthorDaoImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
 
	@Override
	public Author getById(Long id) {
		return authorRepository.getById(id);
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Author saveNewAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Transactional
	@Override
	public Author updateAuthor(Author author) {
		Author foundAuthor = authorRepository.getById(author.getId());
		foundAuthor.setFirstName(author.getFirstName());
		foundAuthor.setLastName(author.getLastName());
		return authorRepository.save(foundAuthor);
	}

	@Override
	public void deleteAuthorById(Long id) {
		authorRepository.deleteById(id);		
	}

	@Override
	public List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
