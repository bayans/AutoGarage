package com.example.autogarage.dto;

import com.example.autogarage.model.Part;
import com.example.autogarage.model.Repair;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProcedureDto {

    public String description;

    public List<PartDto> parts;


    public ProcedureDto(String description, List<PartDto> parts) {
        this.description = description;
        this.parts = parts;

    }

}
