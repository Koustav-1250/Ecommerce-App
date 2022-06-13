package com.example.demo.dto;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;


public class CartItemDto {

    long id;
    Product product;

    int quantity;

    public CartItemDto(Cart cart){
        this.id=cart.getCard_id();
        this.product=cart.getProduct();
        this.quantity=cart.getQuantity();
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
