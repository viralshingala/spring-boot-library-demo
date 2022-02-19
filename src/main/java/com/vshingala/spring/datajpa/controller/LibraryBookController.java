package com.vshingala.spring.datajpa.controller;

import com.vshingala.spring.datajpa.entity.Book;
import com.vshingala.spring.datajpa.entity.Library;
import com.vshingala.spring.datajpa.service.IBookService;
import com.vshingala.spring.datajpa.service.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class LibraryBookController {

    @Autowired
    ILibraryService libraryService;

    @Autowired
    IBookService bookService;


    @RequestMapping(value = "/getAllLibraries", method = RequestMethod.GET)
    public List<Library> getLibraries() {
        return libraryService.getLibraries();
    }

    //
    @RequestMapping(value = "/library", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Library createLibrary(@RequestBody Library library) {
        return libraryService.createLibrary(library);
    }


    @RequestMapping(value = "/library/{libraryId}", method = RequestMethod.GET)
    public Optional<Library> getLibraryById(@PathVariable(value = "libraryId") Long libraryId) {
        return libraryService.getLibraryById(libraryId);
    }

    @RequestMapping(value = "/library/{libraryId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Library updateLibrary(@PathVariable(value = "libraryId") Long libraryId, @RequestBody Library library) {
        return libraryService.updateLibraryById(libraryId, library);
    }

    @RequestMapping(value = "/library/{libraryId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteLibraryById(@PathVariable(value = "libraryId") long libraryId) {
        return libraryService.deleteLibraryById(libraryId);
    }

    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public List<Book> getBooks() {
        return bookService.getAllBooks(null);
    }


    @RequestMapping(value = "/getAllBooks/{libraryId}", method = RequestMethod.GET)
    public List<Book> getBooks(@PathVariable(value = "libraryId") Long libraryId) {
        return bookService.getAllBooks(libraryId);
    }


    //
    @RequestMapping(value = "/{libraryId}/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@PathVariable(value = "libraryId") Long libraryId, @RequestBody Book book) {
        return bookService.createBook(libraryId, book);
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable(value = "bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }


    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book book) {
        return bookService.updateBookById(bookId, book);
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBookById(@PathVariable(value = "bookId") long bookId) {
        return bookService.deleteBookById(bookId);
    }


}