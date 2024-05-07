package com.projet1.serviceStation.service;

import com.projet1.serviceStation.model.Supply;
import com.projet1.serviceStation.repository.SupplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplyService {
    private final SupplyRepository supplyRepository;
    public Supply UpdateSupply(Supply toUpdate){
        return supplyRepository.update(toUpdate);
    }
}
