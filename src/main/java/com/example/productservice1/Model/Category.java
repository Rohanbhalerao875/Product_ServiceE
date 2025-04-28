package com.example.productservice1.Model;

import jakarta.persistence.*;

@Entity
public class Category extends BaseModel {


    String description;
    /*
For one to Many we want the join of table only with the table having ID to one side of the table hence we use oneToMany and map it by Category type where the ManyToOne is configured.
    @OneToMany(mappedBy = "category")
    List<Product> products;
*/


/*
    for description check product where we have mentioned Mappedby products to create only one product
    @ManyToMany
    List<Product> products;
*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
