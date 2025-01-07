package com.example.productservice1.JoinedTable;

import jakarta.persistence.Entity;

@Entity(name = "jt_instructor")
public class Instructor extends User {
    String specilasation;

    public String getSpecilasation() {
        return specilasation;
    }

    public void setSpecilasation(String specilasation) {
        this.specilasation = specilasation;
    }

}
