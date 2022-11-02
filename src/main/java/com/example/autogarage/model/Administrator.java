package com.example.autogarage.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrators")
public class Administrator {

    @Id
    private String name;
    private String lastName;

    public Administrator(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Administrator() {
    }

}
