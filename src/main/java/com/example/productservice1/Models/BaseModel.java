package com.example.productservice1.Models;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass

public class BaseModel {
    @Id
    Long id;
    String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
