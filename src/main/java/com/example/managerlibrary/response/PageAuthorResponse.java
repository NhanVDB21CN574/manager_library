package com.example.managerlibrary.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageAuthorResponse {

    @JsonProperty("total_items")
    private Long totalItems;
    @JsonProperty("authors_list")
    private List<AuthorResponse> authorResponseList;
}
