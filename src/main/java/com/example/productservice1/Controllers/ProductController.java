package com.example.productservice1.Controllers;

import com.example.productservice1.Models.Product;
import com.example.productservice1.Services.ProductService;
import com.example.productservice1.dtos.ProductNotFoundExceptionDTO;
import com.example.productservice1.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;



@RestController
@RequestMapping("/Products")
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier(value = "selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id")Long id) throws ProductNotFoundException {
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
