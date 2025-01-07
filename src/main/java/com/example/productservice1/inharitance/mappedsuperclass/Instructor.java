package com.example.productservice1.inharitance.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "mps_instructor")
public class Instructor extends User {
    public String getSpecilasation() {
        return specilasation;
    }

    public void setSpecilasation(String specilasation) {
        this.specilasation = specilasation;
    }

    String specilasation;

}
