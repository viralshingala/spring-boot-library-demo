package com.vshingala.spring.datajpa.integration;


import com.vshingala.spring.datajpa.dao.LibraryDao;
import com.vshingala.spring.datajpa.entity.Book;
import com.vshingala.spring.datajpa.entity.Library;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LibraryRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LibraryDao libraryDao;

    @Test
    public void getAllLibrariesTest () {
        // given
        Library library = new Library("Lib1", new HashSet<>());
        entityManager.persist(library);
        entityManager.flush();

        // when
        List<Library> found = libraryDao.findAll();

        // then
        Assertions.assertThat(found.size())
                .isEqualTo(1);
    }
}