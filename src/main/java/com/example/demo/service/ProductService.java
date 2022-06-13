package com.example.demo.service;


import com.example.demo.dto.ProductDto;
import com.example.demo.entities.Product;
import com.example.demo.helper.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public ResponseEntity<ApiResponse> addProduct(String token, ProductDto product) throws Exception;

    public ResponseEntity<List<Product>> getAllProduct() throws Exception;

    public ResponseEntity<ApiResponse> deleteProduct(Long id,String Token) throws Exception;

    
}
