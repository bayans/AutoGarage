package com.example.autogarage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProcedureDto {

    public String description;

    @JsonIgnoreProperties({"stock"})
    public List<PartDto> parts;


    public ProcedureDto(String description, List<PartDto> parts) {
        this.description = description;
        this.parts = parts;

    }

}
