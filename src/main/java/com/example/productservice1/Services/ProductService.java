package com.example.productservice1.Services;

import com.example.productservice1.Models.Product;
import com.example.productservice1.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

import javax.management.InstanceNotFoundException;
import java.util.List;


public interface ProductService {




    List<Product> getAllProducts();




    Product getProductById(Long id) throws ProductNotFoundException;


    Product replaceProduct(Long id, Product product);


    Product createProduct(Product product);
}
