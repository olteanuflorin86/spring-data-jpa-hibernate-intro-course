package com.olteanuflorin86.sdjpaintro.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.olteanuflorin86.sdjpaintro.domain.Book;

public interface BookDao {

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
    
    List<Book> findAllBooks();
    
    List<Book> findAllBooks(int pageSize, int offset);
    
    List<Book> findAllBooks(Pageable pageable);
}
