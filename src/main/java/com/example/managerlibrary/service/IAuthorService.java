package com.example.managerlibrary.service;

import com.example.managerlibrary.dto.AuthorDTO;
import com.example.managerlibrary.entity.Author;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.response.PageAuthorResponse;
import org.springframework.data.domain.Pageable;

public interface IAuthorService {
    Author createAuthor(AuthorDTO authorDTO);
    void deleteAuthorById(Long id);
    Author getAuthorById(Long id) throws DataNotFoundException;
    PageAuthorResponse getAllAuthor(String keyword, Pageable pageable);
//    List<Author> getAuthorsByBookId(Long bookId);
    Author updateAuthor(Long id,AuthorDTO authorDTO) throws DataNotFoundException;
}
