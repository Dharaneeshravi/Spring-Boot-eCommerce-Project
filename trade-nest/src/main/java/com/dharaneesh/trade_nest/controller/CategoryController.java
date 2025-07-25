package com.dharaneesh.trade_nest.controller;

import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;


    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategory()
    {
        List<Category> categoryList=categoryService.getAllCategory();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category)
    {
        categoryService.addCategory(category);
        return new ResponseEntity<>("Category added successfully.",HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@Valid @PathVariable Long categoryId)
    {
           String deletedCategory=categoryService.deleteCategory(categoryId);
           return new ResponseEntity<>(deletedCategory,HttpStatus.OK);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @PathVariable Long categoryId,@RequestBody Category category)
    {
            String updateCategory=categoryService.updateCategory(categoryId,category);
            return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }
}
