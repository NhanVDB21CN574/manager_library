package com.example.ManagerLib.service;

import com.example.ManagerLib.dto.LibraryDTO;
import com.example.ManagerLib.entity.Library;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.response.PageLibraryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILibraryService {
    Library createLibrary(LibraryDTO libraryDTO);
    void deleteLibraryById(Long id);
    Library getLibraryById(Long id) throws DataNotFoundException;
    Library updateLibrary(Long id, LibraryDTO libraryDTO) throws DataNotFoundException;
    PageLibraryResponse getAllLibraries(String keyword, Pageable pageable);

}
