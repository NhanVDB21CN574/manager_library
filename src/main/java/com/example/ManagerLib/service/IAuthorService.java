package com.example.ManagerLib.service;

import com.example.ManagerLib.dto.AuthorDTO;
import com.example.ManagerLib.entity.Author;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.response.PageAuthorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAuthorService {
    Author createAuthor(AuthorDTO authorDTO);
    void deleteAuthorById(Long id);
    Author getAuthorById(Long id) throws DataNotFoundException;
    PageAuthorResponse getAllAuthor(String keyword, Pageable pageable);
//    List<Author> getAuthorsByBookId(Long bookId);
    Author updateAuthor(Long id,AuthorDTO authorDTO) throws DataNotFoundException;
}
