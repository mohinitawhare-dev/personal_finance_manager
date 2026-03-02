package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryRepository;
import com.exception.ResourceNotFoundException;
import com.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
    }
    
    
    public List<Category> getCategoriesByType(String type) {
        return categoryRepository.findByType(type);
    }
    
    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = getCategoryById(id);

        if (updatedCategory.getName() != null) category.setName(updatedCategory.getName());
        if (updatedCategory.getType() != null) category.setType(updatedCategory.getType());

        return categoryRepository.save(category);
    }

    
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}