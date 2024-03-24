package com.example.managerlibrary.service;

import com.example.managerlibrary.dto.AuthorDTO;
import com.example.managerlibrary.entity.Author;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.mapper.Mapper;
import com.example.managerlibrary.repository.AuthorRepository;
import com.example.managerlibrary.response.PageAuthorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public PageAuthorResponse getAllAuthor(
            String keyword, Pageable pageable
    ) {
        Page<Long> allAuthorIdsByName = authorRepository.getAllAthorIdsByName(keyword,pageable);
        List<Author> authors = authorRepository.getAllAuthor(allAuthorIdsByName.toList());
        return PageAuthorResponse.builder()
                .totalItems(allAuthorIdsByName.getTotalElements())
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
