package com.example.demo.controller;


import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.UserCart;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.helper.ApiResponse;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @PostMapping("/add/{token}")
    public ResponseEntity<ApiResponse> addToCart(@PathVariable String token, @RequestBody AddToCartDto addToCartDto) throws Exception {
        return this.cartService.addToCart(addToCartDto,token);
    }

    @GetMapping("/{token}")
    public ResponseEntity<UserCart> getCart(@PathVariable String token) throws Exception {
        return this.cartService.getCart(token);
    }



}
