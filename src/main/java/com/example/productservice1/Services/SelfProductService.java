package com.example.productservice1.Services;

import com.example.productservice1.Model.Product;
import com.example.productservice1.exception.ProductNotFoundException;
import com.example.productservice1.repos.CategoryRepo;
import com.example.productservice1.repos.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "selfProductService")
@Primary
public class SelfProductService implements ProductService {
    private final CategoryRepo categoryRepo;
    ProductRepo productRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }


    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepo.findById(id).get();
        // return productRepo.getProductTitleAndDescription(id);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {

        /*
        we are using cascade now so this is not required we were saving the category first in the database if there was no category hence ab jarurat nahi hai because cascade in jpa will handel it thankyou
                Category category = product.getCategory();
                if (category.getId() == null) {
                    Category savedCategory = categoryRepo.save(category);
                    product.setCategory(savedCategory);

                } else {
                    //we should check if category is valid or not or something else.
                }
        */

        return productRepo.save(product);
    }
}
