package com.example.ManagerLib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "libraries")
public class LibraryDTO {

    @JsonProperty( "address")
    private String address;

    @JsonProperty("name_lib")
    private String name;

    @JsonProperty("list_book")
    private List<BookDTO> listBook;

}

