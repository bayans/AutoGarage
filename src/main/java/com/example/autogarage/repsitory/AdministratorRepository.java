package com.example.autogarage.repsitory;

import com.example.autogarage.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator,String> {
}
