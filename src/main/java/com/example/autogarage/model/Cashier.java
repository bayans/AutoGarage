package com.example.autogarage.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "cashiers")
public class Cashier {

    @Id
    private String name;
    private String lastName;

    @OneToMany(mappedBy = "cashier")
    private List<Receipt> receipts;

    public Cashier(String name, String lastName, List<Receipt> receipts) {
        this.name = name;
        this.lastName = lastName;
        this.receipts = receipts;
    }

}
