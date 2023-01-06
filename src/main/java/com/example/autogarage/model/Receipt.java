package com.example.autogarage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    private Long id;
    private Double total;

    private Boolean isPaid;

    @OneToOne
    private Repair repair;

    public Receipt(Long id, Double total, Repair repair) {
        this.id = id;
        this.total = total;

        this.repair = repair;
    }
}
