package com.vshingala.spring.datajpa.integration;


import com.vshingala.spring.datajpa.controller.LibraryBookController;
import com.vshingala.spring.datajpa.entity.Library;
import com.vshingala.spring.datajpa.service.BookService;
import com.vshingala.spring.datajpa.service.LibraryService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryBookController.class)
public class LibraryRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService  libraryService;

    @MockBean
    private BookService bookService;

    @Test
    public void libraryBookControllerTest()
            throws Exception {

        Library library = new Library("Lib1", new HashSet<>());

        List<Library> libraryList = Arrays.asList(library);

        given(libraryService.getLibraries()).willReturn(libraryList);

        mvc.perform(get("/getAllLibraries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)));
    }
}