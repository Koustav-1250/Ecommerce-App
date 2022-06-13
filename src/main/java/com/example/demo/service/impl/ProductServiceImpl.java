package com.example.demo.service.impl;

import com.example.demo.Exceptions.CustomException;
import com.example.demo.dto.ProductDto;
import com.example.demo.entities.AuthenticationToken;
import com.example.demo.entities.Product;
import com.example.demo.entities.ROLE;
import com.example.demo.entities.User;
import com.example.demo.helper.ApiResponse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationTokenService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public TokenRepository tokenRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Override
    public ResponseEntity<ApiResponse> addProduct(String token, ProductDto productDto) throws Exception {

        AuthenticationToken localToken = authenticationTokenService.authenticate(token);

        Optional<User> savedUser=this.userRepository.findById(Long.valueOf(localToken.getUser().getUid()));
        if(savedUser.isPresent()==false){
            throw new CustomException("Forbidden-Request");
        }else {

            if (savedUser.get().getRole().equals(ROLE.ADMIN)) {

                Optional<Product> product = this.productRepository.findByProductName(productDto.getProductName());

                if (product.isPresent() == false) {
                    Product savedProduct = new Product();
                    savedProduct.setProductName(productDto.getProductName());
                    savedProduct.setDescription(productDto.getDescription());
                    savedProduct.setPrice(productDto.getPrice());
                    this.productRepository.save(savedProduct);
                    return new ResponseEntity<>(new ApiResponse(true, savedProduct.getProductName()), HttpStatus.ACCEPTED);
                } else {
                    return new ResponseEntity<>(new ApiResponse(false, "Product Already exits"), HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new CustomException("You are not allowed!!");
            }
        }
    }

    @Override
    public ResponseEntity<List<Product>> getAllProduct() throws Exception {
            List<Product> products=this.productRepository.findAll();
            return new ResponseEntity<>(products,HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteProduct(Long Id,String token) throws Exception {

        AuthenticationToken localToken = authenticationTokenService.authenticate(token);

        Optional<User> savedUser=this.userRepository.findById(Long.valueOf(localToken.getUser().getUid()));
        if(savedUser.isPresent()==false){
            throw new CustomException("Forbidden Request");
        }
        if(savedUser.get().getRole().equals(ROLE.ADMIN)) {
            Optional<Product> optionalProduct = this.productRepository.findById(Id);
            if (!optionalProduct.isPresent())
                return new ResponseEntity<>(new ApiResponse(false, "Product not present"), HttpStatus.NOT_FOUND);

            this.productRepository.deleteById(Id);
            return new ResponseEntity<>(new ApiResponse(true, "Deleted Succesfully"), HttpStatus.ACCEPTED);
        }else{
            throw new CustomException("You are not allowed!!");
        }
    }
}
