package com.projet1.serviceStation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supply {
    private UUID id;
    private UUID idStation;
    private String productType;
    private LocalDateTime supplyDate;
    private double quantity;
}
