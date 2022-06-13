package com.example.demo.repository;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>   {

    List<Cart> findAllByUser(User user);
}
