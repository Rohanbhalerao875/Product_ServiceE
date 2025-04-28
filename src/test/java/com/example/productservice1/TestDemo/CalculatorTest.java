package com.example.productservice1.TestDemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

//     When ---- then ----


    @Test
    void whenAddingTwoNumbersThenResult() {
        //Arrange

        int a = 20;
        int b = 80;
        Calculator calc = new Calculator();
        //ACt
        int result = calc.add(a, b);

        //Assert
//        if(result == 30){
//            System.out.println("Sucesses");
//        }else{
//
//            throw new RuntimeException("Failed");
//        }

        assertEquals(40, result);

    }

    @Test
    void divide() {
        int a = 20;
        int b = 0;
        Calculator calc = new Calculator();
        //ACt
        Assertions.assertThrows(ArithmeticException.class, () -> calc.divide(a, b));
    }
}