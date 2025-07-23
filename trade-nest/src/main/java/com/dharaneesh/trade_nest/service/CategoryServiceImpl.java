package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final List<Category> categoryList=new ArrayList<>();
    private Long categoryId=1l;

    @Override
    public List<Category> getAllCategory() {
        return categoryList;
    }

    @Override
    public void addCategory(Category category) {
        category.setCategoryId(categoryId++);
        categoryList.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category=categoryList.stream().filter(c->c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
            categoryList.remove(category);
            return "Category deleted successfully.";
        }

    @Override
    public String updateCategory(Long categoryId, Category category) {

        Category updateCategory=categoryList.stream().filter(c->c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        updateCategory.setCategoryName(category.getCategoryName());
        return "Category updated successfully.";
    }
}



