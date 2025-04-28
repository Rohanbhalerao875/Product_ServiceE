package com.example.productservice1.repos;

import com.example.productservice1.Model.Product;
import com.example.productservice1.Projections.ProductTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {


    @Query("SELECT p.title as title, p.description as description from Product p where p.id = :id")
    ProductTitleAndDescription getProductTitleAndDescription(@Param("id") Long id);


    @Query(value = "select title,description from product where id =:id", nativeQuery = true)
    ProductTitleAndDescription getProductTitleAndDescriptionSQL(@Param("id") long id);

}
