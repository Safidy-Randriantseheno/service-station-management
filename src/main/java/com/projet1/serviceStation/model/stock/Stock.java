package com.projet1.serviceStation.model.stock;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private UUID id;
    private UUID idProduct;
    private BigDecimal quantity;
    private LocalDateTime lastUpdated;

}
