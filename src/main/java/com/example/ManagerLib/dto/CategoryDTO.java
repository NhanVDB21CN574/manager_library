package com.example.ManagerLib.dto;

import com.example.ManagerLib.entity.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    @JsonProperty("name_category")
    private String nameCategory;
}
