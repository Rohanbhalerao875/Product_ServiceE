package com.example.productservice1.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
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
