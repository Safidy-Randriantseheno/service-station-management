package com.projet1.serviceStation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    private UUID id;
    private String name;
    private String address;
}
