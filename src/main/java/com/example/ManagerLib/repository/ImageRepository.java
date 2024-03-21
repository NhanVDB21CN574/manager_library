package com.example.ManagerLib.repository;

import com.example.ManagerLib.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "select i from Image i left join fetch i.book where i.book.id=:bookId")
    List<Image> getImagesByBookId(Long bookId);


}
