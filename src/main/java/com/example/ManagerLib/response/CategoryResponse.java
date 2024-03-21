package com.example.ManagerLib.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;

    @JsonProperty("name_category")
    private String nameCategory;

    @JsonProperty("number_of_books")
    private int numberOfBooks;



}
