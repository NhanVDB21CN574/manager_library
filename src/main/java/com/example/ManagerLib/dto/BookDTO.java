package com.example.ManagerLib.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
public class BookDTO {

    @JsonProperty("book_title")
    private String bookTitle;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("date_of_publication")
    private LocalDate dateOfPublication;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id_library")
    private Long idLibrary;

    @JsonProperty("id_category")
    private Long idCategory;

    @JsonProperty("list_authors_id")
    private Set<Long> listIdAuthor;

    @JsonProperty("list_url_images")
    private List<String> urlImages;
}
