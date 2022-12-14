package com.example.autogarage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parts")
public class Part {

    @Id
    private String partNumber;
    private double price;
    @OneToOne
    private Stock stock;


    public Part(String partNumber, double price, Stock stock) {
        this.partNumber = partNumber;
        this.price = price;
        this.stock = stock;
    }

}
