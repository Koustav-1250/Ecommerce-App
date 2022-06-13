package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.entities.Product;
import com.example.demo.helper.ApiResponse;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct() throws Exception {
        return this.productService.getAllProduct();
    }

    @PostMapping("/create/{token}")
    public ResponseEntity<ApiResponse> addProduct(@PathVariable String token, @RequestBody ProductDto productDto ) throws Exception {
        return this.productService.addProduct(token , productDto);
    }



    @DeleteMapping("/delete/{token}/{Id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long Id,@PathVariable String token) throws Exception {
        return this.productService.deleteProduct(Id,token);

    }

}
