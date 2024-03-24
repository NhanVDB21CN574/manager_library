package com.example.managerlibrary.repository;

import com.example.managerlibrary.entity.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibraryReporitory extends JpaRepository<Library,Long> {

//    @Query(value = "select distinct l from Library l left join fetch l.listBooks " +
//            "where lower( l.name) like lower(CONCAT('%', :keyword, '%')) ",
//    countQuery = "select count(l) from Library l " +
//            "where lower( l.name) like lower(CONCAT('%', :keyword, '%')) ")
//    Page<Library> getAllLibraries(@Param("keyword") String keyword, Pageable pageable);
//@Query(value = "select distinct l from Library l " +
//        "where lower( l.name) like lower(CONCAT('%', :keyword, '%')) ",
//        countQuery = "select count(l) from Library l " +
//                "where lower( l.name) like lower(CONCAT('%', :keyword, '%')) ")
//Page<Library> getAllLibraries(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select distinct l.id " +
            "from Library l " +
            "where lower( l.name) like lower(CONCAT('%', :keyword, '%')) ",
            countQuery = "select count(l) from Library l where lower( l.name) like lower(CONCAT('%', :keyword, '%'))"
    )
    Page<Long> getAllLibraryIdsByName(@Param("keyword") String keyword, Pageable pageable);

//    @Query(value = "select distinct l " +
//            "from Library l " +
//            "where lower( l.name) like lower(CONCAT('%', :keyword, '%')) ",
//            countQuery = "select count(l) from Library l where lower( l.name) like lower(CONCAT('%', :keyword, '%'))"
//    )
//    Page<Library> getAllLibrariesByName2(@Param("keyword") String keyword, Pageable pageable);
    @Query(value = "select l from Library l " +
            "left join fetch l.listBooks " +
            "where l.id in :ids")
    List<Library> getAllLibraries(@Param("ids") List<Long> ids);

    @Query(value = "select l from Library l left join fetch l.listBooks where l.id=:id")
    Optional<Library> getLibraryById(Long id);
}
