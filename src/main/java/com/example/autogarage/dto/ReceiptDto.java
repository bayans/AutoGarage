package com.example.autogarage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiptDto {
    public Long id;
    public double total;

    private Boolean isPaid;
    public CashierDto cashier;

    public RepairDto repair;

    public ReceiptDto(Long id, double total, CashierDto cashier, RepairDto repair) {
        this.id = id;
        this.total = total;
        this.cashier = cashier;
        this.repair = repair;
    }
}
