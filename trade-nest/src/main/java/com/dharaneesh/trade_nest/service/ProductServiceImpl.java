package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.exception.ResourceNotFoundException;
import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.model.Product;
import com.dharaneesh.trade_nest.payload.ProductDTO;
import com.dharaneesh.trade_nest.payload.ProductResponse;
import com.dharaneesh.trade_nest.repository.CategoryRepository;
import com.dharaneesh.trade_nest.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {

        Product product=modelMapper.map(productDTO,Product.class);
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category"));
        product.setCategory(category);
        product.setImage("default.png");
        double specialPrice= product.getPrice()-((product.getDiscount()*0.01)*product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product addStatus=productRepository.save(product);
        return modelMapper.map(addStatus,ProductDTO.class);
    }
}
