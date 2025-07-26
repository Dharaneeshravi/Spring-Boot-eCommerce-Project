package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.payload.CategoryDTO;
import com.dharaneesh.trade_nest.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize,String sortOrder,String sortBy);

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
}
