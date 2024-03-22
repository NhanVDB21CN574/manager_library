package com.example.managerlibrary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageBookResponse {

    @JsonProperty("total_page")
    private int totalPage;
    @JsonProperty("list_book")
    private List<BookResponse> bookResponseList;
}
