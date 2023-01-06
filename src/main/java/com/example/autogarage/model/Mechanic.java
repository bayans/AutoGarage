package com.example.autogarage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mechanics")
public class Mechanic {

    @Id
    private String name;
    private String lastName;
    @OneToMany( mappedBy = "mechanic", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Car> cars;

    public Mechanic(String name, String lastName, List<Car> cars) {
        this.name = name;
        this.lastName = lastName;
        this.cars = cars;
    }



}
