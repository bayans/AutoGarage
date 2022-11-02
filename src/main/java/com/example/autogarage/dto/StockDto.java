package com.example.autogarage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockDto {
    public Long id;
    public int pieces;

    public StockDto(Long id, int pieces) {
        this.id = id;
        this.pieces = pieces;

    }
}
