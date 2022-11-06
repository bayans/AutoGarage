package com.example.autogarage.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartDto {

    public String partNumber;
    public double price;

    public StockDto stock;


    public PartDto(String partNumber, double price, StockDto stock) {
        this.partNumber = partNumber;
        this.price = price;
        this.stock = stock;
    }

}
