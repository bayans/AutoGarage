package com.example.autogarage.service;



import com.example.autogarage.dto.ProcedureDto;
import com.example.autogarage.exception.RecordNotFoundException;
import com.example.autogarage.model.*;
import com.example.autogarage.repsitory.ProcedureRepository;
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
public class ProcedureService {
    @Autowired
    private ProcedureRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public ProcedureService(ProcedureRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ProcedureDto createProcedure(ProcedureDto procedureDto) {
        Procedure newProcedure = repository.save(modelMapper.map(procedureDto, Procedure.class));
        return modelMapper.map(newProcedure, ProcedureDto.class);
    }

    public List<ProcedureDto> getAllProcedures() {
        return repository.findAll().stream()
                .map(procedure -> modelMapper.map(procedure, ProcedureDto.class)).collect(Collectors.toList());

    }

    public void updateProcedureById(ProcedureDto procedureDto, String id) {
        Optional<Procedure> procedureOptional = repository.findById(id);
        if (procedureOptional.isPresent()) {
            Procedure procedure = procedureOptional.get();
            if (procedureDto.getDescription() != null){
                procedure.setDescription(procedureDto.getDescription());
            }
            if (procedureDto.getParts() != null) {
                procedureDto.getParts().
                        forEach(partDto ->
                                procedure.getParts().add(modelMapper.map(partDto, Part.class)));
            }
            repository.save(procedure);
        } else {
            throw new RecordNotFoundException("procedure with id " + id + " not found");
        }
    }
}
