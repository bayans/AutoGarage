package com.example.autogarage.controller;

import com.example.autogarage.dto.PartDto;
import com.example.autogarage.service.PartService;
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
@RequestMapping(value = "/parts")
public class PartController {

    PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping(value = "")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<PartDto>> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    @PostMapping()
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> createPart(@Valid @RequestBody PartDto carDto, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            PartDto newPartDto = partService.createPart(carDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newPartDto.getPartNumber()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @PutMapping("/{partCode}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> addStock(@Valid @RequestBody PartDto partDto, @PathVariable String
            partCode, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            partService.addStockToPart(partDto, partCode);
            return ResponseEntity.noContent().build();
        }

    }
    @DeleteMapping(value = "/{partCode}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> deletePart(@PathVariable String partCode) {

            partService.deletePartById(partCode);
            return ResponseEntity.noContent().build();

    }

}
