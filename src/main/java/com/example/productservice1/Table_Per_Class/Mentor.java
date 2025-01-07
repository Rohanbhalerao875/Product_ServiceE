package com.example.productservice1.Table_Per_Class;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")

public class Mentor extends User {
    String company;
    String avgRating;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

}
