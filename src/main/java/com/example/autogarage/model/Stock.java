package com.example.autogarage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue
    private Long id;
    private int pieces;

    public Stock(Long id, int pieces) {
        this.id = id;
        this.pieces = pieces;

    }
}
