package com.example.ManagerLib.service;

import com.example.ManagerLib.dto.CategoryDTO;
import com.example.ManagerLib.entity.Category;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.mapper.Mapper;
import com.example.ManagerLib.repository.CategoryRepository;
import com.example.ManagerLib.response.PageCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = Mapper.mapToCategory(categoryDTO);
        Category newCategory = categoryRepository.save(category);
        return newCategory;
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) throws DataNotFoundException {
        Category category = categoryRepository.getCategoryById(id)
                .orElseThrow(()-> new DataNotFoundException("category with id="+id+" not found"));
        return category;
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) throws DataNotFoundException {
        Category category = categoryRepository.getCategoryById(id)
                .orElseThrow(()-> new DataNotFoundException("category with id="+id+" not found"));
        category.setNameCategory(categoryDTO.getNameCategory());
        return categoryRepository.save(category);
    }

    @Override
    public PageCategoryResponse getAllCategories(String keyword,Pageable pageable) {
        Page<Category> categories = categoryRepository.getAllCategories(keyword,pageable);
        PageCategoryResponse pageCategoryResponse = PageCategoryResponse.builder()
                .totalPage(categories.getTotalPages())
                .categoryResponseList(categories.stream().map(category ->
                     Mapper.mapToCategoryResponse(category)
                ).toList())
                .build();
        return pageCategoryResponse;
    }
}
