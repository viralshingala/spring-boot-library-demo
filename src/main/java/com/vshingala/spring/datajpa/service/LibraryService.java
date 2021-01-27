package com.vshingala.spring.datajpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vshingala.spring.datajpa.dao.LibraryDao;
import com.vshingala.spring.datajpa.entity.Library;
import com.vshingala.spring.datajpa.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryDao libraryDao;

    public List<Library> getLibraries() {
        return libraryDao.findAll();
    }


    public Optional<Library> getLibraryById(Long libraryId) {
        if (!libraryDao.existsById(libraryId)) {
            throw new ResourceNotFoundException("Library with id " + libraryId + " not found.");
        }
        return libraryDao.findById(libraryId);
    }


    public Library createLibrary(Library library) {
        return libraryDao.save(library);

    }

    public Library updateLibraryById(Long libraryId, Library library) {
        if (!libraryDao.existsById(libraryId)) {
            throw new ResourceNotFoundException("Library with id " + libraryId + " not found");
        }
        Optional<Library> libraryOptional = libraryDao.findById(libraryId);

        if (!libraryOptional.isPresent()) {
            throw new ResourceNotFoundException("Library with id " + libraryId + " not found");
        }

        Library library1 = libraryOptional.get();
        library1.setName(library.getName());
        return libraryDao.save(library1);

    }

    public ResponseEntity<Object> deleteLibraryById(long libraryId) {
        if (!libraryDao.existsById(libraryId)) {
            throw new ResourceNotFoundException("Library with id " + libraryId + " not found");
        }

        libraryDao.deleteById(libraryId);

        return ResponseEntity.ok().build();

    }


}
