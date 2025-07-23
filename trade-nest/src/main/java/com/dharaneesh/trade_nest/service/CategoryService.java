package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    void addCategory(Category category);

    String deleteCategory(Long categoryId);
}
