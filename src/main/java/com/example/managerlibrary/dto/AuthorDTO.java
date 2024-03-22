package com.example.managerlibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class AuthorDTO {

    @JsonProperty("name")
    private String fullName;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;


}
