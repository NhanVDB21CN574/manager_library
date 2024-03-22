package com.example.managerlibrary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

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
