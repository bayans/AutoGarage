package com.example.autogarage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    private String description;

    @OneToMany
    private List<Part> parts;


    public Procedure(String description, List<Part> parts) {
        this.description = description;
        this.parts = parts;

    }

}
