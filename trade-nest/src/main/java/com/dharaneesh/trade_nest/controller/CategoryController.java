package com.dharaneesh.trade_nest.controller;


import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
