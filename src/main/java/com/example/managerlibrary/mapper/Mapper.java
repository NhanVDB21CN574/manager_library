package com.example.managerlibrary.mapper;

import com.example.managerlibrary.dto.AuthorDTO;
import com.example.managerlibrary.dto.CategoryDTO;
import com.example.managerlibrary.dto.LibraryDTO;
import com.example.managerlibrary.entity.Author;
import com.example.managerlibrary.entity.Book;
import com.example.managerlibrary.entity.Category;
import com.example.managerlibrary.entity.Library;
import com.example.managerlibrary.response.*;

public class Mapper {

    // Map from Entity to Response Class
    public static CategoryResponse mapToCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .nameCategory(category.getNameCategory())
                .numberOfBooks(category.getBookList()==null?0:category.getBookList().size())
                .build();
    }

    public static AuthorResponse mapToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .dateOfBirth(author.getDateOfBirth())
                .numberOfBooks(author.getListBook()==null?0:author.getListBook().size())
                .build();
    }

    public static LibraryResponse mapToLibrabyResponse(Library library){
        return LibraryResponse.builder()
                .id(library.getId())
                .name(library.getName())
                .address(library.getAddress())
                .numberOfBooks(library.getListBooks()==null?0:library.getListBooks().size())
                .build();
    }
    public static BookDetailResponse mapToBookDetailResponse(Book book){
        return BookDetailResponse.builder()
                .id(book.getId())
                .bookTitle(book.getBookTitle())
                .isbn(book.getIsbn())
                .dateOfPublication(book.getDateOfPublication())
                .description(book.getDescription())
                .listAuthors(book.getListAuthor())
                .listImage(book.getListImage())
                .category(book.getCategory())
                .build();
    }
    public static BookResponse mapToBookResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .dateOfPublication(book.getDateOfPublication())
                .bookTitle(book.getBookTitle())
                .description(book.getDescription())
                .category(book.getCategory())
                .build();
    }


    // Map from DTO to Entity Class
    public static Category mapToCategory(CategoryDTO categoryDTO){
        return Category.builder()
                .nameCategory(categoryDTO.getNameCategory())
                .build();
    }
    public static Author mapToAuthor(AuthorDTO authorDTO){
        return Author.builder()
                .fullName(authorDTO.getFullName())
                .dateOfBirth(authorDTO.getDateOfBirth())
                .build();
    }


    public static Library mapToLibrary(LibraryDTO libraryDTO){
        return Library.builder()
                .name(libraryDTO.getName())
                .address(libraryDTO.getAddress())
//                .listBooks(libraryDTO.getListBook())
                .build();
    }


}
