package com.example.depositService.controller;

import com.example.depositService.entity.DebetAccount;
import com.example.depositService.service.DebetAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/debet")
@RequiredArgsConstructor
@Tag(name = "Debet_Account_Service")
public class DebetAccountController {

    private final DebetAccountService debetAccountService;

    @Operation(summary = "Create a new account with zero balance", description = "This endpoint allows creating a new debit account with a zero balance.")
    @ApiResponse(responseCode = "201", description = "Account successfully created")
    @PostMapping("/create")
    public ResponseEntity<Void> createAccount(@RequestBody DebetAccount debetAccount) {
        debetAccountService.createAccount(debetAccount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Deposit funds into the account", description = "This endpoint allows depositing funds into the debit account.")
    @ApiResponse(responseCode = "200", description = "Funds successfully deposited")
    @PutMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody DebetAccount debetAccount) {
        debetAccountService.deposit(debetAccount.getUserId(), debetAccount.getBalance());

        return ResponseEntity.ok().build();
    }

}