package com.example.ManagerLib.controller;

import com.example.ManagerLib.dto.AuthorDTO;
import com.example.ManagerLib.entity.Author;
import com.example.ManagerLib.entity.Book;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.mapper.Mapper;
import com.example.ManagerLib.response.AuthorResponse;
import com.example.ManagerLib.response.PageAuthorResponse;
import com.example.ManagerLib.service.AuthorServiceImpl;
import com.example.ManagerLib.service.IAuthorService;
import com.example.ManagerLib.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthorController {
    private final IAuthorService authorService;

    @PostMapping("")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO authorDTO){
        Author author = authorService.createAuthor(authorDTO);
        AuthorResponse authorResponse = Mapper.mapToAuthorResponse(author);
        return ResponseEntity.ok().body(authorResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable("id") Long id) throws DataNotFoundException {
        Author author = authorService.getAuthorById(id);
        AuthorResponse authorResponse = Mapper.mapToAuthorResponse(author);
        return ResponseEntity.ok().body(authorResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAuthor(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ){
        page= page>0?page-1:0;
        Pageable pageable = PageRequest.of(page,limit, Sort.by("fullName"));
        PageAuthorResponse pageAuthorResponse = authorService.getAllAuthor(keyword,pageable);
        return ResponseEntity.ok().body(pageAuthorResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok().body("Deleted Author successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDTO authorDTO) throws DataNotFoundException {
        Author author = authorService.updateAuthor(id,authorDTO);
        AuthorResponse authorResponse = Mapper.mapToAuthorResponse(author);
        return ResponseEntity.ok().body(authorResponse);
    }


}
