package com.projet1.serviceStation.service;

import com.projet1.serviceStation.model.Transaction;
import com.projet1.serviceStation.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    public Transaction saveTransaction(Transaction toSave){
        return transactionRepository.save(toSave);
    }
}
