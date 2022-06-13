package com.example.demo.service;

import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.UserCart;
import com.example.demo.helper.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {


        ResponseEntity<ApiResponse>  addToCart(AddToCartDto addToCartDto, String token) throws Exception;

        ResponseEntity<UserCart> getCart(String token) throws Exception;



}
