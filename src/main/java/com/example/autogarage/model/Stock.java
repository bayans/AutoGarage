package com.example.autogarage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    private Integer id;
    private int pieces;

    public Stock(Integer id, int pieces) {
        this.id = id;
        this.pieces = pieces;

    }
}
