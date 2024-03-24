package com.example.managerlibrary.repository;

import com.example.managerlibrary.entity.Author;
import com.example.managerlibrary.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    @Query(value = "select c.id from Category c " +
            "where ((length(:keyword)=0) or Lower(c.nameCategory) like LOWER(CONCAT('%', :keyword, '%')))",
            countQuery = "select count(c) from  Category c " +
                    "where ((length(:keyword)=0) or Lower(c.nameCategory) like LOWER(CONCAT('%', :keyword, '%'))) ")
    Page<Long> getAllCategoryIdsByName(String keyword,Pageable pageable);

    @Query(value = "select c from Category c left join fetch c.bookList where c.id in :ids")
    List<Category> getAllCategories(@Param("ids")List<Long> ids);

    @Query("select c from Category c left join fetch c.bookList where c.id=:id")
    Optional<Category> getCategoryById(@Param("id") Long id);

}
