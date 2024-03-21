package com.example.ManagerLib.service;

import com.example.ManagerLib.dto.AuthorDTO;
import com.example.ManagerLib.entity.Author;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.mapper.Mapper;
import com.example.ManagerLib.repository.AuthorRepository;
import com.example.ManagerLib.response.PageAuthorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService{

    private final AuthorRepository authorRepository;


    @Override
    public Author createAuthor(AuthorDTO authorDTO) {
        Author author = Mapper.mapToAuthor(authorDTO);
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author getAuthorById(Long id) throws DataNotFoundException {
        Author author = authorRepository.getAuthorById(id)
                .orElseThrow(()->new DataNotFoundException("Author with id="+id+" not found"));
        return author;
    }

    @Override
    public PageAuthorResponse getAllAuthor(
            String keyword, Pageable pageable
    ) {
        Page<Author> authors = authorRepository.getAllAuthor(keyword, pageable);
        return PageAuthorResponse.builder()
                .totalPages(authors.getTotalPages())
                .authorResponseList(authors.stream().map(author -> Mapper.mapToAuthorResponse(author)).toList())
                .build();
    }

    @Override
    public Author updateAuthor(Long id,AuthorDTO authorDTO) throws DataNotFoundException {
        Author author = authorRepository.getAuthorById(id)
                .orElseThrow(()->new DataNotFoundException("Author with id="+id+" not found"));
        author.setFullName(authorDTO.getFullName());
        author.setDateOfBirth(authorDTO.getDateOfBirth());
        return authorRepository.save(author);
    }
}
