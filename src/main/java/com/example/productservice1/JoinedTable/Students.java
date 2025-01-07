package com.example.productservice1.JoinedTable;

import jakarta.persistence.Entity;

@Entity(name = "jt_student")
public class Students extends User {

    String course;
    String batch;

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

}
