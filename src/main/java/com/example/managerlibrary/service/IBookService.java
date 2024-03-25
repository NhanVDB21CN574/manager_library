package com.example.managerlibrary.service;

import com.example.managerlibrary.dto.BookDTO;
import com.example.managerlibrary.entity.Book;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.exceptions.FileUploadException;
import com.example.managerlibrary.response.PageBookResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface IBookService {
    Book createBook(BookDTO bookDTO) throws DataNotFoundException, FileUploadException;
    void deleteBookById(Long id);
    Book getBookById(Long id) throws DataNotFoundException;
    Book updateBook(Long id,BookDTO bookDTO) throws DataNotFoundException, FileUploadException;
    PageBookResponse searchBook(Long idLib,
                                String isbn,
                                String bookTitle,
                                String category,
                                LocalDate publicationDateFrom,
                                LocalDate publicationDateTo,
                                Pageable pageable);

}
