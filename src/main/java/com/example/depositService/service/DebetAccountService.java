package com.example.depositService.service;

import com.example.depositService.entity.DebetAccount;
import com.example.depositService.repository.DebetAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class DebetAccountService {

    private final DebetAccountRepository debetAccountRepository;

    @Transactional
    public void createAccount(DebetAccount debetAccount) {
        log.info("Создание сущности в бд");
        debetAccount.setBalance(0);
        debetAccountRepository.save(debetAccount);
    }

    @Transactional
    public void deposit(Long userId, Integer balance) {
        log.info("изменение сущности в бд");
        DebetAccount debetAccount = debetAccountRepository.findByUserId(userId);
        if (debetAccount != null) {
            debetAccount.setBalance(debetAccount.getBalance() + balance);
            debetAccountRepository.save(debetAccount);
        }
    }

}



