package com.example.depositService.service;

import com.example.depositService.repository.DebetAccountRepository;
import com.example.depositService.entity.DebetAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DebetAccountService {

    private final DebetAccountRepository debetAccountRepository;

    @Transactional
    public void createAccount(DebetAccount debetAccount) {
        debetAccount.setBalance(0);
        debetAccountRepository.save(debetAccount);
    }

    @Transactional
    public void deposit(Long userId, Integer balance) {
        DebetAccount debetAccount = debetAccountRepository.findByUserId(userId);
        if (debetAccount != null) {
            debetAccount.setBalance(debetAccount.getBalance() + balance);
            debetAccountRepository.save(debetAccount);
        }
    }

}



