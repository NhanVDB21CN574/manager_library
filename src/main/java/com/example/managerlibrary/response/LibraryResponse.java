package com.example.managerlibrary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
