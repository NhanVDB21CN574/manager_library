package com.example.managerlibrary.service;

import com.example.managerlibrary.dto.CategoryDTO;
import com.example.managerlibrary.entity.Category;
import com.example.managerlibrary.exceptions.DataNotFoundException;
import com.example.managerlibrary.mapper.Mapper;
import com.example.managerlibrary.repository.CategoryRepository;
import com.example.managerlibrary.response.PageCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    @Transactional(readOnly = true)
    public PageCategoryResponse getAllCategories(String keyword,Pageable pageable) {
        Page<Long> allCategoryIdsByName = categoryRepository.getAllCategoryIdsByName(keyword,pageable);
        List<Category> categories = categoryRepository.getAllCategories(allCategoryIdsByName.toList());
        PageCategoryResponse pageCategoryResponse = PageCategoryResponse.builder()
                .totalItems(allCategoryIdsByName.getTotalElements())
                .categoryResponseList(categories.stream().map(category ->
                     Mapper.mapToCategoryResponse(category)
                ).toList())
                .build();
        return pageCategoryResponse;
    }
}
