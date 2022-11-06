package com.example.autogarage.service;

import com.example.autogarage.dto.PartDto;
import com.example.autogarage.exception.RecordNotFoundException;
import com.example.autogarage.model.Part;
import com.example.autogarage.model.Stock;
import com.example.autogarage.repsitory.PartRepository;
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
public class PartService {
    @Autowired
    private PartRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public PartService(PartRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public PartDto createPart(PartDto partDto) {
        Part newPart = repository.save(modelMapper.map(partDto, Part.class));
        return modelMapper.map(newPart, PartDto.class);
    }

    public List<PartDto> getAllParts(){
        return repository.findAll().stream().
                map(part -> modelMapper.map(part,PartDto.class)).collect(Collectors.toList());
    }


    public void addStockToPart(PartDto partDto, String partCode) {
        Optional<Part> partOptional = repository.findById(partCode);
        if (partOptional.isPresent()) {
            Part part = partOptional.get();
            if (partDto.getStock() != null) {
                part.setStock(modelMapper.map(partDto.getStock(), Stock.class));
            }
            repository.save(part);
        } else {
            throw new RecordNotFoundException("part with partCode " + partCode + " not found");
        }
    }
    public boolean deletePartById(String partCode) {
            repository.deleteById(partCode);
            return true;
    }


}
