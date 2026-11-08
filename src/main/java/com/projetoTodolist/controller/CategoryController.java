package com.projetoTodolist.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetoTodolist.model.Category;
import com.projetoTodolist.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:5173")

public class CategoryController {

    @Autowired 
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping 
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category createdCategory = categoryService.save(category);
        return ResponseEntity.ok(createdCategory);
    }
}
