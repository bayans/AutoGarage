package com.example.autogarage.repsitory;

import com.example.autogarage.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part,String> {
}
