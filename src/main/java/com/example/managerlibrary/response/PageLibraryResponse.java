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
    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("libraries_list")
    private List<LibraryResponse> libraryResponseList;
}
