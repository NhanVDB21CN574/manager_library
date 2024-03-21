package com.example.ManagerLib.service;

import com.example.ManagerLib.dto.BookDTO;
import com.example.ManagerLib.entity.Book;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.exceptions.FileUploadException;
import com.example.ManagerLib.response.BookResponse;
import com.example.ManagerLib.response.PageBookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IBookService {
    Book createBook(BookDTO bookDTO) throws DataNotFoundException, FileUploadException;
    void deleteBookById(Long id);
    Book getBookById(Long id) throws DataNotFoundException;
    Book updateBook(Long id,BookDTO bookDTO) throws DataNotFoundException;
    PageBookResponse searchBook(Long idLib,
                                String isbn,
                                String bookTitle,
                                String category,
                                LocalDate publicationDateFrom,
                                LocalDate publicationDateTo,
                                Pageable pageable);

}
