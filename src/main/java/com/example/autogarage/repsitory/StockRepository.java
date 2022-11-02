package com.example.autogarage.repsitory;

import com.example.autogarage.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
}
