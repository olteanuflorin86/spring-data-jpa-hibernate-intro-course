package com.olteanuflorin86.sdjpaintro.dao;

import java.util.List;

import com.olteanuflorin86.sdjpaintro.domain.Book;

public interface BookDao {
	
	Book findByISBN(String isbn);
	
    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
    
    List<Book> findAll();

    Book findBookByTitleCriteria(String clean_code);
}
