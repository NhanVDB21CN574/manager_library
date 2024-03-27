package com.example.managerlibrary.controller;

import com.example.managerlibrary.dto.BookDTO;
import com.example.managerlibrary.entity.*;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.exceptions.FileUploadException;
import com.example.managerlibrary.mapper.Mapper;
import com.example.managerlibrary.response.DeleteResponse;
import com.example.managerlibrary.response.PageBookResponse;
import com.example.managerlibrary.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;
    private final ImageServiceImpl imageService;

    @GetMapping("/lib/{id_lib}")
    public ResponseEntity<?> searchBook(
            @PathVariable("id_lib") Long idLib,
            @RequestParam(defaultValue = "",name = "isbn") String isbn,
            @RequestParam(defaultValue = "",name = "book_title") String bookTitle,
            @RequestParam(defaultValue = "",name = "category") String category,
            @RequestParam(defaultValue = "",name = "date_of_publication_from")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate publicationDateFrom,
            @RequestParam(defaultValue = "",name = "date_of_publication_to")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate publicationDateTo,
            @RequestParam(name ="page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "10") int limit
            ){

        try{
            page= page>0?page-1:0;
            Pageable pageable =PageRequest.of(page,limit,Sort.by("bookTitle"));
            PageBookResponse pageBookResponse = bookService.searchBook(idLib,isbn,bookTitle,category,publicationDateFrom,publicationDateTo,pageable);
            return ResponseEntity.ok().body(pageBookResponse);
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id")Long id) throws DataNotFoundException {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok().body(Mapper.mapToBookDetailResponse(book));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id")Long id){
        bookService.deleteBookById(id);
        String message = new String("Deleted book successfully");
        return ResponseEntity.ok().body(DeleteResponse.builder()
                .message(message).build());
    }
    @GetMapping("/images")
    public ResponseEntity<?> viewImage(@RequestParam("url_image") String imageName) throws MalformedURLException {
        try{
                Path imagePath = Paths.get("uploads/"+imageName);
                UrlResource resource = new UrlResource(imagePath.toUri());
            if(resource.exists()){
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            }else {
                return ResponseEntity.badRequest()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(new UrlResource(Paths.get("uploads/notfound.jpeg").toUri()));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new UrlResource(Paths.get("uploads/notfound.jpeg").toUri()));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO){
        try{

            Book book = bookService.createBook(bookDTO);

            return ResponseEntity.ok().body(Mapper.mapToBookDetailResponse(book));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PostMapping("/auto")
//    public ResponseEntity<?> autoCreateBook(){
//        try{
//            List<String> urls = new ArrayList<>();
//            urls.add("3d322784-7f9e-4c50-b6c5-26ce28233a26_abc_book1.jpg");
//            urls.add("09076197-8d4e-4d21-b982-8e54e63907ce_abc_book1.jpg");
//            Book book = bookService.getBookById(1L);
//            Set<Long> ida = new HashSet<>();
//            ida.add(1L);
//            for(int i=1;i<=3000;i++){
//
//                bookService.createBook(BookDTO.builder()
//                        .bookTitle(book.getBookTitle()+i)
//                        .description(book.getDescription()+i)
//                        .dateOfPublication(book.getDateOfPublication())
//                        .isbn(book.getIsbn().charAt(1)+i+"")
//                                .idCategory(2L)
//                                .idLibrary(1L)
//                                .urlImages(urls)
//                                .listIdAuthor(ida)
//                        .build());
//
//            }
//
//            return ResponseEntity.ok().body(Mapper.mapToBookDetailResponse(book));
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id")Long id,BookDTO bookDTO) throws DataNotFoundException, FileUploadException {
        try {

            Book book = bookService.updateBook(id, bookDTO);
            return ResponseEntity.ok().body(book);
        }catch (DataNotFoundException e)
        {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping(value = "/images",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>uploadImages(
            @ModelAttribute("files") List<MultipartFile>files
            )  {
        try{
            List<String> urlImages = imageService.upload(files);
            return ResponseEntity.ok().body(urlImages);
        }catch ( FileUploadException e){
            return ResponseEntity.badRequest().body(e);
        }

//
    }

}


