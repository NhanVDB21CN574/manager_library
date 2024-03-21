package com.example.ManagerLib.repository;

import com.example.ManagerLib.entity.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibraryReporitory extends JpaRepository<Library,Long> {

    @Query(value = "select l from Library l left join fetch l.listBooks " +
            "where lower( l.name) like lower(CONCAT('%', :keyword, '%')) ")
    Page<Library> getAllLibraries(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select l from Library l left join fetch l.listBooks where l.id=:id")
    Optional<Library> getLibraryById(Long id);
}
