package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(nullable = false)
    private String name;

    @Column(length = 500) // Add a description column with a maximum length
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)

    @JsonManagedReference
    private List<Product> products;
    // Getters and Setters
    public Integer getCategoryId() { 
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Method to update fields from another Category object
    public void update(Category categoryDetails) {
        if (categoryDetails.getName() != null) {
            this.name = categoryDetails.getName();
        }
        if (categoryDetails.getDescription() != null) {
            this.description = categoryDetails.getDescription();
        }
    }
}
