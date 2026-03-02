package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exception.ResourceNotFoundException;
import com.model.Category;
import com.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
	public class CategoryController {

	    @Autowired
	    private CategoryService categoryService;

	    
	    @PostMapping("/create")
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	        Category created = categoryService.createCategory(category);
	        return ResponseEntity.status(HttpStatus.CREATED).body(created);
	    }

	    @GetMapping("/all")
	    public ResponseEntity<List<Category>> getAllCategories() {
	        List<Category> categories = categoryService.getAllCategories();
	        return ResponseEntity.ok(categories);
	    }

	    @GetMapping("/get/{id}")
	    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
	        try {
	            return ResponseEntity.ok(categoryService.getCategoryById(id));
	        } catch (ResourceNotFoundException ex) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(ex.getMessage());
	        }
	    }

	    @GetMapping("/type/{type}")
	    public ResponseEntity<List<Category>> getCategoriesByType(@PathVariable String type) {
	        List<Category> categories = categoryService.getCategoriesByType(type);
	        return ResponseEntity.ok(categories);
	    }
	    
	    
	    @PutMapping("/update/{id}")
		 public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody Category category) {
			 try {
				 return ResponseEntity.ok(categoryService.updateCategory(id, category));
			 } catch (ResourceNotFoundException ex) {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(category);
			 }
		 }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
	        try {
	            categoryService.deleteCategory(id);
	            return ResponseEntity.ok("Category deleted successfully");
	        } catch (ResourceNotFoundException ex) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Category not found");
	        }
	    }		 
}

