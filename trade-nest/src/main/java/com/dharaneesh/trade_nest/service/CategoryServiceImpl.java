package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

  @Autowired
  private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
       categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
            categoryRepository.delete(category);
            return "Category deleted successfully.";
        }

    @Override
    public String updateCategory(Long categoryId, Category category) {
        Category updateCategory=categoryRepository.findById(categoryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        updateCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(updateCategory);
        return "Category updated successfully.";
    }
}



