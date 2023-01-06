package com.example.autogarage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String carModel;
    private String clientName;
    private LocalDate registeredDate;
    @ManyToOne
    @JoinColumn(name = "mechanic_name")
    private Mechanic mechanic;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "repair_id")
    private Repair repair;

    public Car(Long id, String carModel, String clientName, LocalDate registeredDate, Mechanic mechanic, Repair repair) {
        this.id = id;
        this.carModel = carModel;
        this.clientName = clientName;
        this.registeredDate = registeredDate;
        this.mechanic = mechanic;
        this.repair = repair;
    }

}
