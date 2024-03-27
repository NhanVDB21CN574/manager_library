package com.example.managerlibrary.controller;

import com.example.managerlibrary.dto.LibraryDTO;
import com.example.managerlibrary.entity.*;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.mapper.Mapper;
import com.example.managerlibrary.response.DeleteResponse;
import com.example.managerlibrary.response.PageLibraryResponse;
import com.example.managerlibrary.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            page= page>0?page-1:0;
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
        String message = new String("delected librabry succesfully");
        return ResponseEntity.ok().body(DeleteResponse.builder()
                .message(message).build());
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
