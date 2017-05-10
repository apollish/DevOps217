package com.capgemini.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.beans.Accounts;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
public class AccountsTest {

	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * create account
	 * 1.when the amount is less than 500 then system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialAmountException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientInitialAmountException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialAmountException
	{
		Accounts account =new Accounts();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenInavlidAccountNumber() throws InvalidAccountNumberException
	{
		accountService.amountDeposit(12345, 123);
	}
	@Test
	public void whenTheAmountDepositedSuccessfully() throws InvalidAccountNumberException
	{
		assertEquals(true, accountService.amountDeposit(123,200));
	}
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenInavlidAccountNumberWhenWithdrawing() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		accountService.withdrawAmount(1234,10000);
	}
	@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
	public void whenTheAmountWithdrawnMoreThanAvailableBal() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		accountService.withdrawAmount(123,30000);
	}
	@Test
	public void whenTheAmountWithdrawnSuccessfully() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		assertEquals(true, accountService.amountDeposit(123,2000));
	}
}
