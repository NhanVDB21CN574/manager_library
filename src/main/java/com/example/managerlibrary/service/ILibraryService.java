package com.example.managerlibrary.service;

import com.example.managerlibrary.dto.LibraryDTO;
import com.example.managerlibrary.entity.Library;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.response.PageLibraryResponse;
import org.springframework.data.domain.Pageable;

public interface ILibraryService {
    Library createLibrary(LibraryDTO libraryDTO);
    void deleteLibraryById(Long id);
    Library getLibraryById(Long id) throws DataNotFoundException;
    Library updateLibrary(Long id, LibraryDTO libraryDTO) throws DataNotFoundException;
    PageLibraryResponse getAllLibraries(String keyword, Pageable pageable);

}
