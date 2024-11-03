package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findByName(String name);
}
