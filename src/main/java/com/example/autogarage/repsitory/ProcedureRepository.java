package com.example.autogarage.repsitory;

import com.example.autogarage.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepository extends JpaRepository<Procedure,String> {
}
