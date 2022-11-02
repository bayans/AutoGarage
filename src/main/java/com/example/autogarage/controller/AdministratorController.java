package com.example.autogarage.controller;

import com.example.autogarage.model.Administrator;
import com.example.autogarage.service.AdministratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admins")
public class AdministratorController {

    AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Administrator>> getAllAdmins() {
        return ResponseEntity.ok(administratorService.getAllAdmins());

    }


}
