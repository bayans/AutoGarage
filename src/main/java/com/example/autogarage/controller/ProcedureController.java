package com.example.autogarage.controller;

import com.example.autogarage.dto.ProcedureDto;
import com.example.autogarage.service.ProcedureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/procedures")
public class ProcedureController {

    ProcedureService procedureService;

    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping(value = "")
    @RolesAllowed({"ROLE_ADMIN","ROLE_MECHANIC"})
    public ResponseEntity<List<ProcedureDto>> getAllProcedures() {
        return ResponseEntity.ok(procedureService.getAllProcedures());
    }

    @PostMapping()
    @RolesAllowed({"ROLE_ADMIN","ROLE_MECHANIC"})
    public ResponseEntity<Object> createProcedure(@Valid @RequestBody ProcedureDto procedureDto, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            procedureService.createProcedure(procedureDto);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_MECHANIC"})
    public ResponseEntity<Object> updateProcedureById
            (@Valid @RequestBody ProcedureDto procedureDto, @PathVariable String id, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            procedureService.updateProcedureById(procedureDto, id);
            return ResponseEntity.noContent().build();
        }
    }
}




