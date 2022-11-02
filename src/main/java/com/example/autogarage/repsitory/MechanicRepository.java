package com.example.autogarage.repsitory;

import com.example.autogarage.model.Car;
import com.example.autogarage.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MechanicRepository extends JpaRepository<Mechanic,String> {

    Optional<Mechanic> findByName(String name);
}
