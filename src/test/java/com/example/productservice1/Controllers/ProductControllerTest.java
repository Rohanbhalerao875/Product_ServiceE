package com.example.productservice1.Controllers;

import com.example.productservice1.Model.Product;
import com.example.productservice1.Services.ProductService;
import com.example.productservice1.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@SpringBootTest
class ProductControllerTest {

//    @MockitoBean
//    private ProductService productService;


    //  @Autowired
    //  ProductController productController;


    // @Test
//    void getProductById() throws ProductNotFoundException {
//
//        // Arrange
//        long productId = 1L;
//        Product product = new Product();
//        product.setId(productId);
//        product.setTitle("test");
//
//        when(productService.getProductById(1L)).thenReturn(product);
//
//        // ACT
//        Product p = productController.getProductById(productId).getBody();
//
//        // Assert
//        Assertions.assertEquals("test", p.getTitle());
//
//    }
//
}