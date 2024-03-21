package com.example.ManagerLib.repository;

import com.example.ManagerLib.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("select c from Category c left join fetch c.bookList " +
            "where (:keyword is null or " +
            "  Lower(c.nameCategory) like LOWER(CONCAT('%', :keyword, '%')))")
    Page<Category> getAllCategories(@Param("keyword") String keyword, Pageable pageable);

    @Query("select c from Category c left join fetch c.bookList where c.id=:id")
    Optional<Category> getCategoryById(@Param("id") Long id);

}
