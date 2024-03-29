package com.vshingala.spring.datajpa.service;

import com.vshingala.spring.datajpa.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vshingala.spring.datajpa.dao.LibraryDao;
import com.vshingala.spring.datajpa.entity.Library;
import com.vshingala.spring.datajpa.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService implements ILibraryService {
    @Autowired
    LibraryDao libraryDao;

//    @Cacheable(value="libraries")
    public List<Library> getLibraries() {
        List<Library> libraries = libraryDao.findAll();
        if(libraries.size() > 0) {
            return libraryDao.findAll();
        }
        throw new NoDataFoundException("No Library data found");
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
