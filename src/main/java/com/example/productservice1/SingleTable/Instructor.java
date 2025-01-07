package com.example.productservice1.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    String specilasation;

    public String getSpecilasation() {
        return specilasation;
    }

    public void setSpecilasation(String specilasation) {
        this.specilasation = specilasation;
    }

}
