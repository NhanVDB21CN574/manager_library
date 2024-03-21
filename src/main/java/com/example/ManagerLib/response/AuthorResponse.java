package com.example.ManagerLib.response;

import com.example.ManagerLib.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponse {
    private Long id;

    @JsonProperty("name")
    private String fullName;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("number_of_books")
    private int numberOfBooks;


}
