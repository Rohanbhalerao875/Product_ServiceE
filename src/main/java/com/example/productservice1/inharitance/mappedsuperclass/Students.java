package com.example.productservice1.inharitance.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name="mps_student")
public class Students extends User{

    String course;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    String batch;

}
