package com.example.autogarage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class RepairDto {

    public Long id;
    @JsonIgnoreProperties({"repair","car"})
    public MechanicDto mechanic;

    public List<ProcedureDto> procedures;

    @JsonIgnoreProperties("repair")
    public ReceiptDto receipt;
    public Boolean isApproved ;

    public RepairDto(Long id, MechanicDto mechanic,
                     List<ProcedureDto> procedures, ReceiptDto receipt, Boolean isApproved) {
        this.id = id;

        this.mechanic = mechanic;
        this.procedures = procedures;
        this.receipt = receipt;
        this.isApproved = isApproved;
    }

}
