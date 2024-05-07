package com.projet1.serviceStation.model.stock;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@Data
public class StockReport {

    private UUID id;
    private UUID stationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<UUID, BigDecimal> productQuantities;
}
