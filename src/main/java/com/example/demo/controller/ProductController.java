package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Category;
import com.example.demo.service.ProductService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")

public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Validate the category
        if (product.getCategory() != null && product.getCategory().getCategoryId() != null) {
            Category category = categoryService.getCategoryById(product.getCategory().getCategoryId());
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Category not found
            }
            product.setCategory(category);
        }
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    // Update a product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        // Validate the category
        if (productDetails.getCategory() != null && productDetails.getCategory().getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productDetails.getCategory().getCategoryId());
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Category not found
            }
            productDetails.setCategory(category); // Set the category for the product
        }

        // Update the product
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return updatedProduct != null ? new ResponseEntity<>(updatedProduct, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Product not found
    }
}
