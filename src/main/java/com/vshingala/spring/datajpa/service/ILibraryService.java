package com.vshingala.spring.datajpa.service;

import com.vshingala.spring.datajpa.entity.Library;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ILibraryService {
    List<Library> getLibraries();
    Optional<Library> getLibraryById(Long libraryId);
    Library createLibrary(Library library);
    Library updateLibraryById(Long libraryId, Library library);
    ResponseEntity<Object> deleteLibraryById(long libraryId);
}
