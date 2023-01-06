package com.example.autogarage.repsitory;

import com.example.autogarage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {

    Optional<Car> findByClientName(String clientName);
}
