package com.example.productservice1.JoinedTable;

import jakarta.persistence.*;


@Entity(name = "jt_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    long id;
    String name;
    String email;
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
