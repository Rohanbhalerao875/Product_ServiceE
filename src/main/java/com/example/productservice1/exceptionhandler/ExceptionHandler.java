package com.example.productservice1.exceptionhandler;

import com.example.productservice1.dtos.ProductNotFoundExceptionDTO;
import com.example.productservice1.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDTO> handleInstanceNotFound(ProductNotFoundException e) {

        ProductNotFoundExceptionDTO productNotFoundExceptionDTO = new ProductNotFoundExceptionDTO();
        productNotFoundExceptionDTO.setErrorCode(e.getId());
        productNotFoundExceptionDTO.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(productNotFoundExceptionDTO, HttpStatus.NOT_FOUND);
    }
}
