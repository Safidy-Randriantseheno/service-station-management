package com.projet1.serviceStation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private UUID id;
    private UUID idStation;
    private UUID idProduct;
    private BigDecimal quantitySold;
    private BigDecimal saleAmount;
    private LocalDateTime transactionDate;
}
