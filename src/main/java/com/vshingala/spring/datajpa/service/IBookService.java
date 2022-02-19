package com.vshingala.spring.datajpa.service;

import com.vshingala.spring.datajpa.entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks(Long libraryId);
    Book getBookById(Long bookId);
    Book createBook(Long linraryId, Book book);
    Book updateBookById(Long bookId, Book bookRequest);
    ResponseEntity<Object> deleteBookById(long bookId);
}
