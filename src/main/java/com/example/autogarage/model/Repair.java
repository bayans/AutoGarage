package com.example.autogarage.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "repairs")
public class Repair {
    @Id
    private Long id;
    @ManyToOne
    private Mechanic mechanic;
    @OneToMany
    private List<Procedure> procedures;
    @OneToOne
    private Receipt receipt;
    private Boolean isApproved;

    public Repair(Long id,Mechanic mechanic, List<Procedure> procedures,
                  Receipt receipt, Boolean isApproved) {
        this.id = id;
        this.mechanic = mechanic;
        this.procedures = procedures;
        this.receipt = receipt;
        this.isApproved = isApproved;
    }
}
