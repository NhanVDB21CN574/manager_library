package com.example.managerlibrary.service;

import com.example.managerlibrary.dto.BookDTO;
import com.example.managerlibrary.entity.*;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.exceptions.FileUploadException;
import com.example.managerlibrary.mapper.Mapper;
import com.example.managerlibrary.repository.*;
import com.example.managerlibrary.response.PageBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService{
    private final BookRepository bookRepository;
    private final CategoryServiceImpl categoryService;
    private final LibraryServiceImpl libraryService;
    private final ImageServiceImpl imageService;
    private final AuthorServiceImpl authorService;

    @Override
    public Book createBook(BookDTO bookDTO) throws DataNotFoundException, FileUploadException {
        Category category = categoryService.getCategoryById(bookDTO.getIdCategory());
        Library library = libraryService.getLibraryById(bookDTO.getIdLibrary());

        Set<Author> authors = new HashSet<>();
        bookDTO.getListIdAuthor().forEach(authorId->{
            try {
                authors.add(authorService.getAuthorById(authorId));
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        Book book = Book.builder()
                .bookTitle(bookDTO.getBookTitle())
                .isbn(bookDTO.getIsbn())
                .dateOfPublication(bookDTO.getDateOfPublication())
                .description(bookDTO.getDescription())
                .category(category)
                .library(library)
                .listAuthor(authors)
                .build();
        Book addedBook = bookRepository.save(book);
        List<Image> images = imageService.createListImage(bookDTO.getUrlImages(),addedBook);
        addedBook.setListImage(images.stream().collect(Collectors.toSet()));
        return addedBook;
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getBookById(Long id) throws DataNotFoundException {
        Book book = bookRepository.getBookById(id)
                .orElseThrow(()->new DataNotFoundException("Book not found"));
        return book;
    }

    @Override
    public Book updateBook(Long id, BookDTO bookDTO) throws DataNotFoundException {
        Set<Author> authorSet = new HashSet<>();
        bookDTO.getListIdAuthor().forEach(authorId-> {
            try {
                authorSet.add(authorService.getAuthorById(authorId));
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Book book = bookRepository.getBookById(id)
                .orElseThrow(()->new DataNotFoundException("Book not found"));
        book.setBookTitle(bookDTO.getBookTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setDateOfPublication(bookDTO.getDateOfPublication());
        book.setCategory(categoryService.getCategoryById(bookDTO.getIdCategory()));
        book.setListAuthor(authorSet);
        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }

    @Override
    public PageBookResponse searchBook(Long idLib,String isbn, String bookTitle, String category, LocalDate publicationDateFrom, LocalDate publicationDateTo, Pageable pageable) {

        Page<Book> bookPage = bookRepository.searchBook(idLib,isbn,bookTitle,category,
                publicationDateFrom,publicationDateTo,pageable);
        return PageBookResponse.builder()
                .totalItems(bookPage.getTotalElements())
                .bookResponseList(bookPage.stream()
                        .map(book -> Mapper.mapToBookResponse(book)).collect(Collectors.toList()))
                .build();
    }




}
