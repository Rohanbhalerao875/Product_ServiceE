package com.example.productservice1.Controllers;

import com.example.productservice1.Model.Product;
import com.example.productservice1.Services.ProductService;
import com.example.productservice1.exception.ProductNotFoundException;
import com.example.productservice1.validateToken.TokenService;
import org.hibernate.cache.spi.access.UnknownAccessTypeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/Products")
public class ProductController {

    ProductService productService;
//    TokenService tokenService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
//        this.tokenService = tokenService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id")Long id) throws ProductNotFoundException {
//        if(!tokenService.validateToken(token)){
//            throw new UnknownAccessTypeException("User does not have access to this product");
//        }


        Product product = null;

            product = productService.getProductById(id);
            ResponseEntity<Product> response;
//        if (product == null) {
//            return ResponseEntity.notFound().build(); // Return 404 if not found
//        }
        response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable ("id")Long id ,@RequestBody Product product){
        return productService.replaceProduct(id,product);
    }
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ProductNotFoundExceptionDTO> handleInstanceNotFound(ProductNotFoundException e) {
//
//        ProductNotFoundExceptionDTO productNotFoundExceptionDTO = new ProductNotFoundExceptionDTO();
//        productNotFoundExceptionDTO.setErrorCode(e.getId());
//        productNotFoundExceptionDTO.setErrorMessage(e.getMessage());
//        return new ResponseEntity<>(productNotFoundExceptionDTO,HttpStatus.NOT_FOUND);
//    }


}
