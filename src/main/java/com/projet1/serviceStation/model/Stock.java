package com.projet1.serviceStation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private UUID id;
    private UUID stationId;
    private UUID productId;
    private double quantity;
    private double evaporationRate;
    private LocalDateTime lastUpdated;

}
