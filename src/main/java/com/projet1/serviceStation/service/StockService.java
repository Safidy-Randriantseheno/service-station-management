package com.projet1.serviceStation.service;

import com.projet1.serviceStation.model.stock.Stock;
import com.projet1.serviceStation.model.stock.StockMovement;
import com.projet1.serviceStation.model.stock.StockReport;
import com.projet1.serviceStation.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    public List<Stock> findAll(){
        return stockRepository.findAll();
    }
    public List<StockMovement> getStockMovementForDateRange(UUID idStation, LocalDate startDate, LocalDate endDate){
        return stockRepository.getStockMovementsForDateRange(idStation,startDate,endDate);
    }
    public StockReport generateStockReport(UUID idStation, LocalDate startDate, LocalDate endDate){
        return stockRepository.generateStockReport(idStation,startDate,endDate);
    }
}
