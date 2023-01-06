package com.example.autogarage.controller;


import com.example.autogarage.dto.RepairDto;
import com.example.autogarage.service.RepairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/repairs")
public class RepairController {

    RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping(value = "")
    @RolesAllowed({"ROLE_ADMIN","ROLE_MECHANIC"})
    public ResponseEntity<List<RepairDto>> getAllRepairs() {
        return ResponseEntity.ok(repairService.getAllRepairs());
    }

    @PostMapping()
    @RolesAllowed({"ROLE_ADMIN","ROLE_MECHANIC"})
    public ResponseEntity<Object> createRepair(@Valid @RequestBody RepairDto repairDto, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            repairService.createRepair(repairDto);
            return ResponseEntity.noContent().build();
        }
    }
    @PutMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_MECHANIC"})
    public ResponseEntity<Object> updateRepairById(@Valid @RequestBody RepairDto repairDto, @PathVariable Long id, BindingResult br) {

        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            repairService.updateRepairById(repairDto, id);
            return ResponseEntity.noContent().build();
        }
    }
}
