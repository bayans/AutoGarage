package com.example.autogarage.service;

import com.example.autogarage.dto.CarDto;
import com.example.autogarage.dto.RepairDto;
import com.example.autogarage.exception.RecordNotFoundException;
import com.example.autogarage.model.*;
import com.example.autogarage.repsitory.CarRepository;
import com.example.autogarage.repsitory.RepairRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepairService {
    @Autowired
    private RepairRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public RepairService(RepairRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public RepairDto createRepair(RepairDto repairDto) {
        Repair newRepair = repository.save(modelMapper.map(repairDto, Repair.class));
        return modelMapper.map(newRepair, RepairDto.class);
    }

    public RepairDto getRepairById(Long id) {
        Optional<Repair> repairOptional = repository.findById(id);
        if (!repairOptional.isPresent()) {
            throw new RecordNotFoundException("repair not found");
        } else {
            Repair repair = repairOptional.get();
            return modelMapper.map(repair, RepairDto.class);
        }
    }

    public List<RepairDto> getAllRepairs() {
        return repository.findAll().stream()
                .map(repair -> modelMapper.map(repair, RepairDto.class)).collect(Collectors.toList());

    }

    public void updateRepairById(RepairDto repairDto, Long id) {
        Optional<Repair> repairOptional = repository.findById(id);
        if (repairOptional.isPresent()) {
            Repair repair = repairOptional.get();
            if (repairDto.getIsApproved() != null) {
                repair.setIsApproved(repairDto.getIsApproved());
            }
            if (repairDto.getMechanic() != null) {
                repair.setMechanic(modelMapper.map(repairDto.getMechanic(), Mechanic.class));
            }
            if (repairDto.getProcedures() != null) {
                repairDto.getProcedures().
                        forEach(procedureDto ->
                                repair.getProcedures().add(modelMapper.map(procedureDto, Procedure.class)));
            }
            if (repairDto.getReceipt() != null) {
                repair.setReceipt(modelMapper.map(repairDto.getReceipt(), Receipt.class));
            }
            repository.save(repair);
        } else {
            throw new RecordNotFoundException("repair with id " + id + " not found");
        }
    }
}




