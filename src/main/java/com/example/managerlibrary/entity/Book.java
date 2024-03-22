package com.example.managerlibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title")
    @JsonProperty("book_title")
    private String bookTitle;

    @JsonProperty("isbn")
    @Column(name = "isbn",unique = true)
    private String isbn;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    @JsonProperty("date_of_publication")
    @Column(name = "date_of_publication")
    private LocalDate dateOfPublication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lib")
    private Library library;

    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Image> listImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category category;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author")
    )
    private Set<Author> listAuthor;

}
