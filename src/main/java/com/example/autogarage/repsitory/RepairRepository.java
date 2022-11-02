package com.example.autogarage.repsitory;

import com.example.autogarage.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair,Long> {
}
