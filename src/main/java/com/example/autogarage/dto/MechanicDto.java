package com.example.autogarage.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class MechanicDto {
    private String name;
    private String lastName;

    @JsonIgnoreProperties("mechanic")
    public List<CarDto> cars;

    public MechanicDto(String name, String lastName, List<CarDto> cars) {
        this.name = name;
        this.lastName = lastName;
        this.cars = cars;
    }

}
