package com.example.autogarage.service;


import com.example.autogarage.model.Administrator;
import com.example.autogarage.repsitory.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository repository;

    public AdministratorService(AdministratorRepository repository) {
        this.repository = repository;
    }

    public List<Administrator> getAllAdmins() {
        return repository.findAll();
    }

}
