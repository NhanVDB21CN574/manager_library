package com.example.managerlibrary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageCategoryResponse {

    @JsonProperty("total_items")
    private Long totalItems;

    @JsonProperty("categories_list")
    private List<CategoryResponse> categoryResponseList;
}
