package com.example.autogarage.service;


import com.example.autogarage.dto.MechanicDto;
import com.example.autogarage.exception.RecordNotFoundException;
import com.example.autogarage.model.Mechanic;
import com.example.autogarage.repsitory.MechanicRepository;
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
public class MechanicService {
    @Autowired
    private MechanicRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public MechanicService(MechanicRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public MechanicDto createMechanic(MechanicDto mechanicDto) {
        Mechanic newMechanic = repository.save(modelMapper.map(mechanicDto, Mechanic.class));
        return modelMapper.map(newMechanic, MechanicDto.class);
    }

    public List<MechanicDto> getAllMechanics() {
        return repository.findAll().stream().
                map(mechanic -> modelMapper.map(mechanic, MechanicDto.class)).collect(Collectors.toList());
    }

    public MechanicDto findByName(String name) {
        Optional<Mechanic> mechanicOptional = repository.findByName(name);
        if (!mechanicOptional.isPresent()) {
            throw new RecordNotFoundException("Mechanic not found");
        } else {
            Mechanic mechanic = mechanicOptional.get();
            return modelMapper.map(mechanic, MechanicDto.class);
        }
    }


}
