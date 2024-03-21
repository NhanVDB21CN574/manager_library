package com.example.ManagerLib.response;

import com.example.ManagerLib.entity.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibraryResponse {


    private Long id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("name_lib")
    private String name;

    @JsonProperty("number_of_books")
    private int numberOfBooks;

}
