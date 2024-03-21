package com.example.ManagerLib.dto;

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
@Table(name = "authors")
public class AuthorDTO {

    @JsonProperty("name")
    private String fullName;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;


}
