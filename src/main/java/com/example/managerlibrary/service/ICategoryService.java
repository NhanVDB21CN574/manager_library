package com.example.managerlibrary.service;

import com.example.managerlibrary.dto.CategoryDTO;
import com.example.managerlibrary.entity.Category;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.response.PageCategoryResponse;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    void deleteCategoryById(Long id);
    Category getCategoryById(Long id) throws DataNotFoundException;
    Category updateCategory(Long id, CategoryDTO categoryDTO) throws DataNotFoundException;
    PageCategoryResponse getAllCategories(String keyword,Pageable pageable);
}
