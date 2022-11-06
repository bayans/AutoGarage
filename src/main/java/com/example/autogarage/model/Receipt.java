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
