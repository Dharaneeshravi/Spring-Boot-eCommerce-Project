package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategory();

    void addCategory(Category category);

    String deleteCategory(Long categoryId);

    String updateCategory(Long categoryId, Category category);
}
