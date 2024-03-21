package com.example.ManagerLib.service;

import com.example.ManagerLib.dto.CategoryDTO;
import com.example.ManagerLib.entity.Category;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.response.PageCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    void deleteCategoryById(Long id);
    Category getCategoryById(Long id) throws DataNotFoundException;
    Category updateCategory(Long id, CategoryDTO categoryDTO) throws DataNotFoundException;
    PageCategoryResponse getAllCategories(String keyword,Pageable pageable);
}
