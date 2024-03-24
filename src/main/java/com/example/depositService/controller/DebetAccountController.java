package com.example.depositService.controller;

import com.example.depositService.entity.DebetAccount;
import com.example.depositService.kafka.KafkaProducer;
import com.example.depositService.service.DebetAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.depositService.kafka.KafkaConsumer.TOPIC;

@RestController
@RequestMapping("/api/v1/debet")
@RequiredArgsConstructor
public class DebetAccountController {

    private final KafkaProducer kafkaProducer;
    private final DebetAccountService debetAccountService;

    @PostMapping("/create")
    public ResponseEntity<Void> createAccount(@RequestBody DebetAccount debetAccount) {
        debetAccountService.createAccount(debetAccount);

        kafkaProducer.sendMessage(TOPIC, "Account created: " + debetAccount.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DebetAccount debetAccount) {
        debetAccountService.deposit(debetAccount.getUserId(), debetAccount.getBalance());

        String message = "Deposit for user " + debetAccount.getUserId() + ": " + debetAccount.getBalance();
        kafkaProducer.sendMessage(TOPIC, message);

        return ResponseEntity.ok().build();
    }

}