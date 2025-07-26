package com.dharaneesh.trade_nest.controller;

import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.payload.CategoryDTO;
import com.dharaneesh.trade_nest.payload.CategoryResponse;
import com.dharaneesh.trade_nest.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;


    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategory(
            @RequestParam(name ="pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue ="1",required = false) Integer pageSize
    )
    {
        CategoryResponse categoryList=categoryService.getAllCategory(pageNumber,pageSize);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO status= categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@Valid @PathVariable Long categoryId)
    {
           CategoryDTO deletedCategory=categoryService.deleteCategory(categoryId);
           return new ResponseEntity<>(deletedCategory,HttpStatus.OK);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @PathVariable Long categoryId,@RequestBody CategoryDTO categoryDTO)
    {
            CategoryDTO updateCategory=categoryService.updateCategory(categoryId,categoryDTO);
            return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }
}
