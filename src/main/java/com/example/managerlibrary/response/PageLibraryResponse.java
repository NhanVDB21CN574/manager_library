package com.example.managerlibrary.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageLibraryResponse {
    @JsonProperty("total_items")
    private Long totalItems;

    @JsonProperty("libraries_list")
    private List<LibraryResponse> libraryResponseList;
}
