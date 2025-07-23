package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.model.Category;
import org.springframework.stereotype.Service;

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

    @Override
    public String updateCategory(Long categoryId, Category category) {

        Optional<Category> optionalCategory=categoryList.stream().filter(c->c.getCategoryId().equals(categoryId)).findFirst();

        if(optionalCategory.isPresent())
        {
            Category updateCategory=optionalCategory.get();
            updateCategory.setCategoryName(category.getCategoryName());
            return "Category updated successfully.";
        }
        else
        {
            return "Category not found";
        }
    }
}
