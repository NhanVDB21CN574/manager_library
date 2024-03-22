package com.example.ManagerLib.controller;

import com.example.ManagerLib.dto.BookDTO;
import com.example.ManagerLib.dto.LibraryDTO;
import com.example.ManagerLib.entity.*;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.exceptions.FileUploadException;
import com.example.ManagerLib.mapper.Mapper;
import com.example.ManagerLib.response.LibraryResponse;
import com.example.ManagerLib.response.PageLibraryResponse;
import com.example.ManagerLib.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/libraries")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LibraryController {
    private final ILibraryService libraryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getLibreryById(@PathVariable("id") Long id) throws DataNotFoundException {
        Library library = libraryService.getLibraryById(id);
        return ResponseEntity.ok().body(Mapper.mapToLibrabyResponse(library));
    }
    @GetMapping("")
    public ResponseEntity<?> getAllLibraries(
            @RequestParam(defaultValue = "",name = "keyword") String keyword,
            @RequestParam(name ="page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "10") int limit
    ){
        try{
            Pageable pageable = PageRequest.of(page,limit, Sort.by("name"));
            PageLibraryResponse libraries = libraryService.getAllLibraries(keyword,pageable);
            return ResponseEntity.ok().body(libraries);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }


//    @PostMapping(value = "/create",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @PostMapping("")
    public ResponseEntity<?> insertLibrary(
            @RequestBody LibraryDTO libraryDTO
    ){
        Library library = libraryService.createLibrary(libraryDTO);

        return ResponseEntity.ok().body(Mapper.mapToLibrabyResponse(library));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibrary(
            @PathVariable("id")Long id
    ){
        libraryService.deleteLibraryById(id);
        return ResponseEntity.ok().body("delected librabry succesfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibrary(
            @PathVariable("id") Long id,
            @RequestBody LibraryDTO libraryDTO
    ) throws DataNotFoundException {
        Library library = libraryService.updateLibrary(id,libraryDTO);
        return ResponseEntity.ok().body(Mapper.mapToLibrabyResponse(library));
    }


}
