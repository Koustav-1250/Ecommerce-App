package com.example.demo.dto;

import java.util.List;

public class UserCart {

    List<CartItemDto> cartItems;
    int totalPayout;

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public int getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(int totalPayout) {
        this.totalPayout = totalPayout;
    }
}
