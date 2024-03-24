package com.example.managerlibrary.repository;

import com.example.managerlibrary.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long>, PagingAndSortingRepository<Book,Long> {


    @Query(value = "select DISTINCT  b " +
            "from Book b  left join fetch b.category " +
            "where (b.library.id=:idLib) " +
            "and (:bookTitle is null Or lower(b.bookTitle) like LOWER(CONCAT('%', :bookTitle, '%'))) " +
            "and (:isbn is null or lower(b.isbn) like LOWER(CONCAT('%', :isbn, '%'))) " +
            "and (:category is null or lower(b.category.nameCategory) like LOWER(CONCAT('%', :category, '%'))) " +
            "and (:publicationDateFrom is null  or b.dateOfPublication >= :publicationDateFrom) " +
            "and (:publicationDateTo is null or b.dateOfPublication <= :publicationDateTo)",
        countQuery = "select count(b) from Book b " +
                "where (b.library.id=:idLib) " +
                "and (:bookTitle is null Or lower(b.bookTitle) like LOWER(CONCAT('%', :bookTitle, '%'))) " +
                "and (:isbn is null or lower(b.isbn) like LOWER(CONCAT('%', :isbn, '%'))) " +
                "and (:category is null or lower(b.category.nameCategory) like LOWER(CONCAT('%', :category, '%'))) " +
                "and (:publicationDateFrom is null  or b.dateOfPublication >= :publicationDateFrom) " +
                "and (:publicationDateTo is null or b.dateOfPublication <= :publicationDateTo)"
    )
    Page<Book> searchBook(@Param("idLib") Long idLib,
                          @Param("isbn") String isbn,
                          @Param("bookTitle") String bookTitle,
                          @Param("category") String category,
                          @Param("publicationDateFrom") LocalDate publicationDateFrom,
                          @Param("publicationDateTo") LocalDate publicationDateTo,
                          Pageable pageable);




    @Query(value = "select DISTINCT b " +
            "from Book b left join fetch b.library left join fetch b.category left join fetch b.listAuthor left join fetch b.listImage " +
            "where b.id=:id ")
    Optional<Book> getBookById(@Param("id") Long id);




}
