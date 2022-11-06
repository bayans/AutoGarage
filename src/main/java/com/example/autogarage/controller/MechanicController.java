package com.example.autogarage.controller;

import com.example.autogarage.dto.CarDto;
import com.example.autogarage.dto.MechanicDto;
import com.example.autogarage.service.MechanicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/mechanics")
public class MechanicController {

    MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @GetMapping(value = "")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<MechanicDto>> getAllMechanics() {
        return ResponseEntity.ok(mechanicService.getAllMechanics());
    }

    @PostMapping()
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> createMechanic(@Valid @RequestBody MechanicDto mechanicDto, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            MechanicDto newMechanicDto = mechanicService.createMechanic(mechanicDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newMechanicDto.getName()).toUri();
            return ResponseEntity.created(location).build();
        }
    }
}
