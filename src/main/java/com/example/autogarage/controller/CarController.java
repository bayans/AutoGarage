package com.example.autogarage.controller;

import com.example.autogarage.dto.CarDto;
import com.example.autogarage.service.CarService;
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
@RequestMapping(value = "/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping(value = "/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<CarDto> getOneCar(@PathVariable Long id) {
        CarDto carDto = carService.getCarById(id);
        return ResponseEntity.ok(carDto);
    }

    @PostMapping()
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarDto carDto, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            CarDto newCarDto = carService.createCar(carDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newCarDto.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @PutMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> AssignMechanicToCar(@Valid @RequestBody CarDto carDto, @PathVariable Long id, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            carService.assignCarToMechanic(carDto, id);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/assignRepair/{id}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_MECHANIC"})
    public ResponseEntity<Object> updateRepairForCarByCarId(@Valid @RequestBody CarDto carDto, @PathVariable Long id, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            carService.assignRepairToCar(carDto, id);
            return ResponseEntity.noContent().build();
        }
    }
    @PutMapping("calculateReceipt/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> calculateTotalReceiptByCarId(@PathVariable Long id) {

            carService.calculateTotalReceiptByCarId(id);
            return ResponseEntity.noContent().build();
        }


}
