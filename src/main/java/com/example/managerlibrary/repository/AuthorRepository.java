package com.example.managerlibrary.repository;

import com.example.managerlibrary.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query(value = "select a.id from Author a " +
            "where ((length(:keyword)=0) or Lower(a.fullName) like LOWER(CONCAT('%', :keyword, '%')))",
    countQuery = "select count(a) from  Author a " +
            "where ((length(:keyword)=0) or Lower(a.fullName) like LOWER(CONCAT('%', :keyword, '%'))) ")

    /**
    *@keyword keyword to search for in the author names.
    *
     */
    Page<Long> getAllAthorIdsByName(String keyword,Pageable pageable);

    @Query(value = "select a from Author a left join fetch a.listBook where a.id in :ids")
    List<Author> getAllAuthor(@Param("ids")List<Long> ids);

    @Query(value = "select a from Author a left join fetch a.listBook " +
            "where a.id=:id")
    Optional<Author> getAuthorById(Long id);

}
