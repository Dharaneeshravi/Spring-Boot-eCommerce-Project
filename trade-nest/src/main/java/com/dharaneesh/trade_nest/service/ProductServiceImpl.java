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

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ProductResponse getAllProduct() {

        List<Product> productList=productRepository.findAll();
        List<ProductDTO> productDTOS=productList.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse getProductByCategory(Long categoryId) {

        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException(categoryId,"categoryId","Category"));
        List<Product> productList=productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOS=productList.stream().map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;

    }

    @Override
    public ProductResponse getProductByKeyword(String keyword) {

        List<Product> productList=productRepository.findByProductNameLikeIgnoreCase("%"+keyword+"%");
        List<ProductDTO> productDTOS=productList.stream().map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProductById(Long productId, ProductDTO productDTO) {

        Product product=modelMapper.map(productDTO,Product.class);
        Product updateProduct=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId,"productId","Product"));
        updateProduct.setProductName(product.getProductName());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setQuantity(product.getQuantity());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDiscount(product.getDiscount());
        double specialPrice=product.getPrice()-((product.getDiscount()*0.01)*product.getPrice());
        updateProduct.setSpecialPrice(specialPrice);
        Product updateStatus=productRepository.save(updateProduct);
        return modelMapper.map(updateStatus,ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProductById(Long productId) {

        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException(productId,"productId","Product"));
        productRepository.delete(product);
        return modelMapper.map(product,ProductDTO.class);

    }
}
