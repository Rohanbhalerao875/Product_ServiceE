package com.example.productservice1.Model;


import jakarta.persistence.*;

@Entity
public class Product extends BaseModel {


    private String description;
    private Double price;
    int quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Category category;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    //For ManytoMany we need to specify with mappedBy as well where ever is fine or else it will create two mapping tables and that will create a problem
    // @ManyToMany(mappedBy = "product")
    // List<Category> categories;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



}
