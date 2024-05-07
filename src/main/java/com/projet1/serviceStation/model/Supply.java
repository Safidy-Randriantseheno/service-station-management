package com.projet1.serviceStation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supply {
    private UUID id;
    private Station station;
    private Product product;
    private LocalDate dateSupply;
    private double quantity;
}
