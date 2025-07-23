package com.dharaneesh.trade_nest.controller;


import com.dharaneesh.trade_nest.service.CategoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
