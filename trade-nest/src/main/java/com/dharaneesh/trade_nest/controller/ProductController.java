package com.dharaneesh.trade_nest.controller;

import com.dharaneesh.trade_nest.payload.ProductDTO;
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
}
