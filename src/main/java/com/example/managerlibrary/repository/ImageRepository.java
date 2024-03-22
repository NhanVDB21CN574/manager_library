package com.example.managerlibrary.repository;

import com.example.managerlibrary.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "select i from Image i left join fetch i.book where i.book.id=:bookId")
    List<Image> getImagesByBookId(Long bookId);


}
