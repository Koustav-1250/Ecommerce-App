package com.example.demo.service.impl;

import com.example.demo.Exceptions.CustomException;
import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.UserCart;
import com.example.demo.dto.CartItemDto;
import com.example.demo.entities.AuthenticationToken;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.helper.ApiResponse;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationTokenService;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse> addToCart(AddToCartDto addToCartDto, String token) throws Exception {


//        //authenticate the token
            AuthenticationToken localToken = authenticationTokenService.authenticate(token);

            User savedUser=localToken.getUser();

            if (savedUser==null) {
                throw new CustomException("Please register");
            }

            Optional<Product> productOptional = this.productRepository.findById(addToCartDto.getProductId());

            if (productOptional.isPresent() == false) {
                throw new CustomException("Product Not Exist");
            } else {

                Cart cart = new Cart();
                cart.setProduct(productOptional.get());
                cart.setUser(savedUser);
                cart.setQuantity(addToCartDto.getQuantity());

                this.cartRepository.save(cart);

                return new ResponseEntity<>(new ApiResponse(true,"Added to cart of " + cart.getUser().getUserName()), HttpStatus.CREATED);
            }
    }

    @Override
    public ResponseEntity<UserCart> getCart(String token) throws Exception {

        AuthenticationToken dbToken=authenticationTokenService.authenticate(token);

        User user = dbToken.getUser();

        List<Cart> cartList = this.cartRepository.findAllByUser(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        int totalPrice=0;
        for(Cart item : cartList){
            CartItemDto cartItemDto=new CartItemDto(item);
            totalPrice+=cartItemDto.getProduct().getPrice()*cartItemDto.getQuantity();
            cartItems.add(cartItemDto);
        }
        UserCart userCart=new UserCart();
        userCart.setCartItems(cartItems);
        userCart.setTotalPayout(totalPrice);

        return new ResponseEntity<>(userCart,HttpStatus.ACCEPTED);
    }


}
