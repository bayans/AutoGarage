package com.example.autogarage.controller;

import com.example.autogarage.dto.CarDto;
import com.example.autogarage.dto.PartDto;
import com.example.autogarage.dto.StockDto;
import com.example.autogarage.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/stocks")
public class StockController {

    StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping()
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> createStock(@Valid @RequestBody StockDto stockDto, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            StockDto newStockDto = stockService.createStock(stockDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newStockDto.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }


}