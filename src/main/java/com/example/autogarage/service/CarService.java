package com.example.autogarage.service;


import com.example.autogarage.dto.CarDto;
import com.example.autogarage.dto.RepairDto;
import com.example.autogarage.exception.RecordNotFoundException;
import com.example.autogarage.model.Car;
import com.example.autogarage.model.Mechanic;
import com.example.autogarage.model.Repair;
import com.example.autogarage.repsitory.CarRepository;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@Accessors(chain = true)
@Service
public class CarService {
    @Autowired
    private CarRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public CarService(CarRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public CarDto createCar(CarDto carDto) {
        Car newCar = repository.save(modelMapper.map(carDto, Car.class));
        return modelMapper.map(newCar, CarDto.class);
    }

    public List<CarDto> getAllCars() {
        return repository.findAll().stream()
                .map(car -> modelMapper.map(car, CarDto.class)).collect(Collectors.toList());

    }

    public CarDto getCarById(Long id) {
        Optional<Car> partOptional = repository.findById(id);
        if (!partOptional.isPresent()) {
            throw new RecordNotFoundException("car not found");
        } else {
            Car car = partOptional.get();
            return modelMapper.map(car, CarDto.class);
        }
    }


    public CarDto findByClientName(String name) {
        Optional<Car> carOptional = repository.findByClientName(name);
        if (!carOptional.isPresent()) {
            throw new RecordNotFoundException("car not found");
        } else {
            Car car = carOptional.get();
            return modelMapper.map(car, CarDto.class);
        }
    }

    public void assignCarToMechanic(CarDto carDto, Long id) {
        Optional<Car> carOptional = repository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            if (carDto.getMechanic() != null) {
                car.setMechanic(modelMapper.map(carDto.getMechanic(), Mechanic.class));
            }
            repository.save(car);
        } else {
            throw new RecordNotFoundException("car with id " + id + " not found");
        }
    }

    public void assignRepairToCar(CarDto carDto, Long id) {
        Optional<Car> carOptional = repository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            if (carDto.getRepair() != null) {
                car.setRepair(modelMapper.map(carDto.getRepair(), Repair.class));
            }
            repository.save(car);
        } else {
            throw new RecordNotFoundException("car with id " + id + " not found");
        }
    }

}
