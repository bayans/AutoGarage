package com.example.autogarage.dto;

import com.example.autogarage.model.Receipt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CashierDto {

    public String name;
    public String lastName;
    @JsonIgnore
    public List<ReceiptDto> receipts;

    public CashierDto(String name, String lastName, List<ReceiptDto> receipts) {
        this.name = name;
        this.lastName = lastName;
        this.receipts = receipts;
    }


}
