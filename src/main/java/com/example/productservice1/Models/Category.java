package com.example.productservice1.Models;

import jakarta.persistence.Entity;

@Entity
public class Category extends BaseModel {
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
