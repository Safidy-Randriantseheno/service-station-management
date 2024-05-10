package com.projet1.serviceStation.controller;

import com.projet1.serviceStation.model.Supply;
import com.projet1.serviceStation.service.SupplyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@AllArgsConstructor
@RequestMapping("/supply")
public class SupplyController {
    private final SupplyService supplyService;
@PostMapping("/new")
    public Supply updateSupply( @RequestBody Supply toUpdate){
        return supplyService.UpdateSupply(toUpdate);
    }
}
