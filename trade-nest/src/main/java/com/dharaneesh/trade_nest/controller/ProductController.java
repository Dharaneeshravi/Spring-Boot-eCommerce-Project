package com.dharaneesh.trade_nest.controller;

import com.dharaneesh.trade_nest.payload.ProductDTO;
import com.dharaneesh.trade_nest.payload.ProductResponse;
import com.dharaneesh.trade_nest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@PathVariable Long categoryId, @RequestBody ProductDTO productDTO)
    {
        ProductDTO addStatus=productService.addProduct(categoryId,productDTO);
        return new ResponseEntity<>(addStatus, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProduct()
    {
        ProductResponse getStatus=productService.getAllProduct();
        return new ResponseEntity<>(getStatus,HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/product")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable Long categoryId)
    {
        ProductResponse getStatus=productService.getProductByCategory(categoryId);
        return new ResponseEntity<>(getStatus,HttpStatus.OK);
    }

    @GetMapping("/public/product/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword)
    {
        ProductResponse getStatus=productService.getProductByKeyword(keyword);
        return new ResponseEntity<>(getStatus,HttpStatus.OK);
    }

    @PutMapping("/admin/product/{productId}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long productId,@RequestBody ProductDTO productDTO)
    {
        ProductDTO updateStatus=productService.updateProductById(productId,productDTO);
        return new ResponseEntity<>(updateStatus,HttpStatus.OK);
    }
    @DeleteMapping("/admin/product/{productId}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long productId)
    {
        ProductDTO deleteStatus=productService.deleteProductById(productId);
        return new ResponseEntity<>(deleteStatus,HttpStatus.OK);
    }

    @PutMapping("/admin/product/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId,@RequestParam MultipartFile image) throws IOException {
        ProductDTO updateImage=productService. updateProductImage(productId,image);
        return new ResponseEntity<>(updateImage,HttpStatus.OK);
    }
}
