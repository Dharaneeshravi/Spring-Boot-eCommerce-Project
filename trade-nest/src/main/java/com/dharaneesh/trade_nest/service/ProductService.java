package com.dharaneesh.trade_nest.service;

import com.dharaneesh.trade_nest.payload.ProductDTO;
import com.dharaneesh.trade_nest.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, ProductDTO productDTO);

    ProductResponse getAllProduct(Integer pageNumber,Integer pageSize,String sortOrder,String sortBy);

    ProductResponse getProductByCategory(Long categoryId,Integer pageNumber,Integer pageSize,String sortOrder,String sortBy);

    ProductResponse getProductByKeyword(String keyword);

    ProductDTO updateProductById(Long productId, ProductDTO productDTO);

    ProductDTO deleteProductById(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
