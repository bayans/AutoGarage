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
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double total;

    private Boolean isPaid;
    @ManyToOne
    private Cashier cashier;
    @OneToOne
    private Repair repair;

    public Receipt(Long id, double total, Cashier cashier, Repair repair) {
        this.id = id;
        this.total = total;
        this.cashier = cashier;
        this.repair = repair;
    }
}
