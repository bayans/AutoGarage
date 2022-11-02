package com.example.autogarage.service;

import com.example.autogarage.repsitory.CashierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashierService {
    @Autowired
    private CashierRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public CashierService(CashierRepository repository,ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }



}
