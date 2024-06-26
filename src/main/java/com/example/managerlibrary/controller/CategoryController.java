package com.example.managerlibrary.controller;

import com.example.managerlibrary.dto.CategoryDTO;
import com.example.managerlibrary.entity.Category;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.mapper.Mapper;
import com.example.managerlibrary.response.CategoryResponse;
import com.example.managerlibrary.response.DeleteResponse;
import com.example.managerlibrary.response.PageCategoryResponse;
import com.example.managerlibrary.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(
            @RequestParam(name = "keyword",defaultValue = "") String keyword,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "10") int limit
    ){
        page= page>0?page-1:0;
        Pageable pageable = PageRequest.of(page,limit, Sort.by("nameCategory"));
        PageCategoryResponse pageCategoryResponse = categoryService.getAllCategories(keyword,pageable);
        return ResponseEntity.ok().body(pageCategoryResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) throws DataNotFoundException {
        Category category = categoryService.getCategoryById(id);
        CategoryResponse categoryResponse = Mapper.mapToCategoryResponse(category);
        return ResponseEntity.ok().body(categoryResponse);
    }
    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok().body(Mapper.mapToCategoryResponse(category));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
        String message = new String("Deleted category successfully");
        return ResponseEntity.ok().body(DeleteResponse.builder()
                .message(message).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable("id")Long id,
            @RequestBody CategoryDTO categoryDTO
    ) throws DataNotFoundException {
        Category category = categoryService.updateCategory(id,categoryDTO);
        return ResponseEntity.ok().body(category);
    }





}
