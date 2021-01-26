package com.vshingala.spring.datajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vshingala.spring.datajpa.dao.BookDao;
import com.vshingala.spring.datajpa.dao.LibraryDao;
import com.vshingala.spring.datajpa.entity.Book;
import com.vshingala.spring.datajpa.entity.Library;
import com.vshingala.spring.datajpa.exception.ResourceNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {


    @Autowired
    BookDao bookDao;

    @Autowired
    LibraryDao libraryDao;

    public List<Book> getAllBooks(Long libraryId) {
        if (libraryId != null)
            return bookDao.findAllByLibrary_id(libraryId);
        else
            return bookDao.findAll();
    }


    public Optional<Book> getBookById(Long bookId) {
        if (!bookDao.existsById(bookId)) {
            throw new ResourceNotFoundException("Book with id " + bookId + " not found");
        }
        return bookDao.findById(bookId);
    }


    public Book createBook(Long linraryId, Book book) {
        Set<Book> books = new HashSet<>();
        Library linrary1 = new Library();

        Optional<Library> byId = libraryDao.findById(linraryId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Library with id " + linraryId + " does not exist");
        }
        Library library = byId.get();

        book.setLibrary(library);

        Book book1 = bookDao.save(book);
        books.add(book1);
        linrary1.setBooks(books);

        return book1;

    }

    public Book updateBookById(Long bookId, Book bookRequest) {
        if (!bookDao.existsById(bookId)) {
            throw new ResourceNotFoundException("Book with id " + bookId + " not found");
        }
        Optional<Book> book = bookDao.findById(bookId);

        if (!book.isPresent()) {
            throw new ResourceNotFoundException("Book with id " + bookId + " not found");
        }

        Book book1 = book.get();
        book1.setGenre(bookRequest.getGenre());
        book1.setTitle(bookRequest.getTitle());

        return bookDao.save(book1);
    }

    public ResponseEntity<Object> deleteBookById(long bookId) {
        if (!bookDao.existsById(bookId)) {
            throw new ResourceNotFoundException("Book with id " + bookId + " not found");
        }

        bookDao.deleteById(bookId);

        return ResponseEntity.ok().build();

    }
}
