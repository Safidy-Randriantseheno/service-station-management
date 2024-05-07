package com.projet1.serviceStation.model.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {
    private UUID id;
    private UUID idStation;
    private UUID idProduct;
    private LocalDateTime movementDate;
    private String movementType;
    private BigDecimal quantity;
}