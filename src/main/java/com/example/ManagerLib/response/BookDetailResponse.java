package com.example.ManagerLib.response;

import com.example.ManagerLib.entity.Author;
import com.example.ManagerLib.entity.Category;
import com.example.ManagerLib.entity.Image;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("book_title")
    private String bookTitle;

    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("date_of_publication")
    private LocalDate dateOfPublication;

    @JsonProperty("description")
    private String description;

    @JsonProperty("images_list")
    private List<Image> listImage;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("authors_list")
    private Set<Author> listAuthors;

}
