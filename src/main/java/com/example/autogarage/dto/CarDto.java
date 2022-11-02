package com.example.autogarage.dto;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CarDto {

    private Long id;
    public String carModel;
    public String clientName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate registeredDate;

    @JsonIgnoreProperties("cars")
    public MechanicDto mechanic;


    @JsonIgnoreProperties({"mechanic"})
    public RepairDto repair;

    public CarDto(Long id, String carModel, String clientName,
                  LocalDate registeredDate, MechanicDto mechanic, RepairDto repair) {
        this.id = id;
        this.carModel = carModel;
        this.clientName = clientName;
        this.registeredDate = registeredDate;
        this.mechanic = mechanic;
        this.repair = repair;
    }

}
