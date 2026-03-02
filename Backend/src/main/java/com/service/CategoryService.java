package com.service;

import java.util.List;

import com.model.Category;

public interface CategoryService {
	
	
  public  Category createCategory(Category category);

    
  public  List<Category> getAllCategories();

    
  public  Category getCategoryById(Long id);

    
  public  Category updateCategory(Long id, Category category);

    
  public  void deleteCategory(Long id);

    
   public List<Category> getCategoriesByType(String type);

    
}
