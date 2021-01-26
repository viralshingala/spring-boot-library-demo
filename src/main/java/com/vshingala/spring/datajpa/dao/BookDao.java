package com.vshingala.spring.datajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vshingala.spring.datajpa.entity.Book;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

    List<Book> findAllByLibrary_id(Long libraryId);

}
