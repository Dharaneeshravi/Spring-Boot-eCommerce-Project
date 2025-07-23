package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        Category category=categoryList.stream().filter(c->c.getCategoryId().equals(categoryId)).findFirst().orElse(null);

        if(category!=null)
        {
            categoryList.remove(category);
            return "Category deleted successfully.";
        }
        else
        {
            return "Category not found";
        }
    }
}
