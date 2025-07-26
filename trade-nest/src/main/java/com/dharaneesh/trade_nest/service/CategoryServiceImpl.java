package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.exception.APIException;
import com.dharaneesh.trade_nest.exception.ResourceNotFoundException;
import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.payload.CategoryDTO;
import com.dharaneesh.trade_nest.payload.CategoryResponse;
import com.dharaneesh.trade_nest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService{

  @Autowired
  private  CategoryRepository categoryRepository;

  @Autowired
  private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize,String sortOrder,String sortBy) {

        Sort sort=sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page= PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> categoryPage=categoryRepository.findAll(page);
        List<Category> categoryList=categoryPage.getContent();

        if(categoryList.isEmpty())
        {
            throw new APIException("No categories available at this time.");
        }
        List<CategoryDTO> categoryDTOS=categoryList.stream()
                .map(category ->modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElement(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;

    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {

        Category category=modelMapper.map(categoryDTO,Category.class);

        Category response=categoryRepository.findByCategoryName(category.getCategoryName());

        if(response!=null)
        {
            throw new APIException("Duplicate category name. Please choose a different name.");
        }
       Category addStatus= categoryRepository.save(category);
        return modelMapper.map(addStatus,CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category"));
        categoryRepository.delete(category);
        return modelMapper.map(category,CategoryDTO.class);
        }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {

        Category category=modelMapper.map(categoryDTO,Category.class);
        Category updateCategory=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category"));
        updateCategory.setCategoryName(category.getCategoryName());
        Category updateStatus= categoryRepository.save(updateCategory);
        return modelMapper.map(updateStatus,CategoryDTO.class);
    }
}



