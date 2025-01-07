package com.example.productservice1.Table_Per_Class;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "tpc_instructor")

public class Instructor extends User {
    String specilasation;

    public String getSpecilasation() {
        return specilasation;
    }

    public void setSpecilasation(String specilasation) {
        this.specilasation = specilasation;
    }

}
