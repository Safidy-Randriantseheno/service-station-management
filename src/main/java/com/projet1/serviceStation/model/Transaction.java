package com.projet1.serviceStation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private UUID id;
    private UUID stationId;
    private UUID productId;
    private LocalDate dateSale;
    private double quantity;
    private double totalPrice;
}
