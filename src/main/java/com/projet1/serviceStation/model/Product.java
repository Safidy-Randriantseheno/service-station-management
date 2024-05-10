package com.projet1.serviceStation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID id;
    private ProductTemplate productTemplate;
    private Station idStation;
    private BigDecimal evaporationRate;
    private Double quantity;

    public Product(ProductTemplate productTemplate, Station station, Double quantity) {
    }
}
