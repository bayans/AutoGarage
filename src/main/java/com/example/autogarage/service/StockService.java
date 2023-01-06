package com.example.autogarage.service;

import com.example.autogarage.dto.StockDto;
import com.example.autogarage.model.*;
import com.example.autogarage.repsitory.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockService {
    @Autowired
    private StockRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public StockService(StockRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public StockDto createStock(StockDto stockDto) {
        Stock newStock = repository.save(modelMapper.map(stockDto, Stock.class));
        return modelMapper.map(newStock, StockDto.class);
    }



}
