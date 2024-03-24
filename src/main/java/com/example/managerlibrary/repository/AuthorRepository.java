package com.example.managerlibrary.repository;

import com.example.managerlibrary.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
//    @Query(value = "Select * from authors a where a.id in " +
//            "(select id_author from author_book ab where ab.id_book=:id_book)"
//            ,nativeQuery = true)
//    List<Author> getAuthorsByBookId(@Param("id_book")Long bookId);

    @Query(value = "select a from Author a left join fetch a.listBook where ((length(:keyword)=0) or " +
            "Lower(a.fullName) like LOWER(CONCAT('%', :keyword, '%')))",
    countQuery = "select count(a) from Author a where ((length(:keyword)=0) or Lower(a.fullName) like LOWER(CONCAT('%', :keyword, '%')))")
    Page<Author> getAllAuthor(String keyword,Pageable pageable);

//    @Query(value = "select a from Author a left join fetch a.listBook " +
//            "where a.id=:id")
//    Optional<Author> getAuthorById(Long id);
    @Query(value = "select a from Author a  " +
            "where a.id=:id")
    Optional<Author> getAuthorById(Long id);
}
