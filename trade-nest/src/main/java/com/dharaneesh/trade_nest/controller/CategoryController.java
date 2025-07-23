package com.dharaneesh.trade_nest.controller;


import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public List<Category> getAllCategory()
    {
        List<Category> categoryList=categoryService.getAllCategory();
        return categoryList;
    }

    @PostMapping("/api/admin/categories")
    public String addCategory(@RequestBody Category category)
    {
        categoryService.addCategory(category);
        return "Category added successfully.";
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId)
    {
        String deletedCategory=categoryService.deleteCategory(categoryId);
        return deletedCategory;
    }
}
