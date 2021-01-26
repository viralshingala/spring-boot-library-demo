package com.vshingala.spring.datajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vshingala.spring.datajpa.entity.Library;

@Repository
public interface LibraryDao extends JpaRepository<Library, Long> {

}
