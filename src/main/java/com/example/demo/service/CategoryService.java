package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
       
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
         
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
    
    
    public List<Product> getProductsByCategoryName(String categoryName) {
        Optional<Category> categoryOpt = categoryRepository.findByName(categoryName);
        return categoryOpt.map(Category::getProducts).orElseThrow(() -> 
            new RuntimeException("Category not found")
        );
    }
//    public Category updateCategory(Integer id, Category categoryDetails) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
//        
//        // Update the category properties
//        category.setName(categoryDetails.getName());
//        category.setDescription(categoryDetails.getDescription());
//        
//        // Save the updated category
//        return categoryRepository.save(category);
//    }
    
    public Category updateCategory(Integer id, Category categoryDetails) {
        Category category = getCategoryById(id); // Fetch category or throw an exception if not found
        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        return categoryRepository.save(category);
    }


    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
