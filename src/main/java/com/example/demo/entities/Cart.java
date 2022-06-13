package com.example.demo.entities;


import javax.persistence.*;


@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long card_id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    int quantity;

    public Cart() {

    }

    public long getCard_id() {
        return card_id;
    }

    public void setCard_id(long card_id) {
        this.card_id = card_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

