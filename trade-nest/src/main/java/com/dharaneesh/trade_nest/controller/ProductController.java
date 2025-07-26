package com.dharaneesh.trade_nest.controller;

import com.dharaneesh.trade_nest.payload.ProductDTO;
import com.dharaneesh.trade_nest.payload.ProductResponse;
import com.dharaneesh.trade_nest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}
