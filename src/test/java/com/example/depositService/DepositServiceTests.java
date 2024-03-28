package com.example.depositService;

import com.example.depositService.controller.DebetAccountController;
import com.example.depositService.entity.DebetAccount;
import com.example.depositService.service.DebetAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DepositServiceTests {

	@Mock
	private DebetAccountService debetAccountService;

	@InjectMocks
	private DebetAccountController debetAccountController;

	@Test
	public void testCreateAccount_Success() {
		DebetAccount debetAccount = new DebetAccount();
		doNothing().when(debetAccountService).createAccount(debetAccount);

		ResponseEntity<Void> response = debetAccountController.createAccount(debetAccount);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(debetAccountService, times(1)).createAccount(debetAccount);
	}

	@Test
	public void testDeposit_Success() {
		DebetAccount debetAccount = new DebetAccount();
		ResponseEntity<Void> response = debetAccountController.deposit(debetAccount);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(debetAccountService, times(1)).deposit(debetAccount.getUserId(), debetAccount.getBalance());
	}

	@Test
	public void testCreateAccount_Failure() {
		DebetAccount debetAccount = new DebetAccount();
		doThrow(new RuntimeException("Duplicate entry")).when(debetAccountService).createAccount(debetAccount);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> debetAccountController.createAccount(debetAccount));

		assertEquals("Duplicate entry", exception.getMessage());
	}

}
