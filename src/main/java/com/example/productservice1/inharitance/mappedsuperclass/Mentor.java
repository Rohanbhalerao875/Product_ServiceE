package com.example.productservice1.inharitance.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "mps_mentor")
public class Mentor extends User {
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

    String company;
    String avgRating;

}
