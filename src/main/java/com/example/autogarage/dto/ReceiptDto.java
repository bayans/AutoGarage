package com.example.autogarage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiptDto {
    public Long id;
    public Double total;

    private Boolean isPaid;

    public RepairDto repair;

    public ReceiptDto(Long id, Double total, RepairDto repair) {
        this.id = id;
        this.total = total;

        this.repair = repair;
    }
}
