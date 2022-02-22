package com.vshingala.spring.datajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "Book")
public class Book  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID", nullable = false, length = 10)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Title")
    @NotBlank(message = "Enter a title ")
    private String title;

    @Column(name = "Genre")
    @NotBlank(message = "Enter a genre ")
    private String genre;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Library_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Library library;

    public Book() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    //getter method to retrieve the LibraryId
    public Long getLibrary_id(){
        return library.getId();
    }

    //getter Method to get the Library's full name
    public String getLibraryName(){
        return library.getName();
    }

    @JsonIgnore
    public Library getLibrary() {
        return library;
    }

    @JsonIgnore
    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
