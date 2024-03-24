package com.example.depositService;

import com.example.depositService.controller.DebetAccountController;
import com.example.depositService.entity.DebetAccount;
import com.example.depositService.service.DebetAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class DepositServiceTests {

	@Autowired
	private DebetAccountController debetAccountController;

	@Autowired
	private DebetAccountService debetAccountService;

	@Test
	void testCreateAccountWithZeroBalance() {
//		// Создание моков для сервиса и контроллера
//		DebetAccount debetAccount = new DebetAccount();
//		debetAccount.setUserId(123L);
//		debetAccount.setBalance(0);
//
//		when(debetAccountService.createAccount(debetAccount)).thenReturn(debetAccount);
//
//		// Вызов контроллера для создания счета
//		ResponseEntity<Void> responseEntity = debetAccountController.createAccount(debetAccount);
//
//		// Проверка ответа
//		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//
//		// Проверка вызова сервиса
//		verify(debetAccountService, times(1)).createAccount(debetAccount);
	}
}
