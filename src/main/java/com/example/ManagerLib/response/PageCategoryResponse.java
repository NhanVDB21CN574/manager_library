package com.example.ManagerLib.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageCategoryResponse {

    @JsonProperty("total_page")
    private int totalPage;

    @JsonProperty("categories_list")
    private List<CategoryResponse> categoryResponseList;
}
