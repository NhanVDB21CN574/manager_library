package com.example.ManagerLib.service;

import com.example.ManagerLib.dto.BookDTO;
import com.example.ManagerLib.dto.LibraryDTO;
import com.example.ManagerLib.entity.*;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.exceptions.FileUploadException;
import com.example.ManagerLib.mapper.Mapper;
import com.example.ManagerLib.repository.BookRepository;
import com.example.ManagerLib.repository.CategoryRepository;
import com.example.ManagerLib.repository.LibraryReporitory;
import com.example.ManagerLib.response.PageLibraryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements ILibraryService{

    private final LibraryReporitory libraryReporitory;
    private final CategoryServiceImpl categoryService;
    private final AuthorServiceImpl authorService;
    private final BookRepository bookRepository;
    private final ImageServiceImpl imageService;

    @Override
    public Library createLibrary(LibraryDTO libraryDTO) {
        Library library = Library.builder()
                .address(libraryDTO.getAddress())
                .name(libraryDTO.getName())
                .build();

        Library newLibrary = libraryReporitory.save(library);

        List<BookDTO> bookDTOList = libraryDTO.getListBook();
        List<Book> addedBooks = new ArrayList<>();
        bookDTOList.forEach(bookDTO -> {

            Set<Author> authors = new HashSet<>();
            bookDTO.getListIdAuthor().forEach(id-> {
                            try {
                                 authors.add( authorService.getAuthorById(id));
                            } catch (DataNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        });
            try {
                Book newBook = bookRepository.save(Book.builder().bookTitle(bookDTO.getBookTitle())
                        .isbn(bookDTO.getIsbn())
                        .dateOfPublication(bookDTO.getDateOfPublication())
                        .description(bookDTO.getDescription())
                        .library(library)
                        .category(categoryService.getCategoryById(bookDTO.getIdCategory()))
                        .listAuthor(authors)
                        .build());

                List<String> urlImages = bookDTO.getUrlImages();
                List<Image> addedImages = imageService.createListImage(urlImages,newBook);
                newBook.setListImage(addedImages);
                addedBooks.add(newBook);
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileUploadException e) {
                throw new RuntimeException(e);
            }


        });
        newLibrary.setListBooks(addedBooks);
        return newLibrary;

    }

    @Override
    public void deleteLibraryById(Long id) {
        libraryReporitory.deleteById(id);
    }

    @Override
    public Library getLibraryById(Long id) throws DataNotFoundException {
        Library existLibrary = libraryReporitory.getLibraryById(id)
                .orElseThrow(()-> new DataNotFoundException("Library with id="+id+" not found"));
        return existLibrary;

    }

    @Override
    public Library updateLibrary(Long id, LibraryDTO libraryDTO) throws DataNotFoundException {
        Library existLibrary = getLibraryById(id);
        existLibrary.setAddress(libraryDTO.getAddress());
        existLibrary.setName(libraryDTO.getName());
        return libraryReporitory.save(existLibrary);
    }

    @Override
    public PageLibraryResponse getAllLibraries(String keyword, Pageable pageable) {
        Page<Library> allLibraries= libraryReporitory.getAllLibraries(keyword,pageable);
        return PageLibraryResponse.builder()
                .totalPages(allLibraries.getTotalPages())
                .libraryResponseList(allLibraries.stream()
                        .map(library -> Mapper.mapToLibrabyResponse(library)).toList())
                .build();
    }
}
