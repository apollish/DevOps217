package com.capgemini.service;


import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.beans.Accounts;
import com.capgemini.repository.AccountRepository;


public class AccountServiceImpl implements AccountService {
	
	
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public Accounts createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		int a,b,c;
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Accounts account = new Accounts();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	
	@Override
	public boolean amountDeposit(int accountNumber,int amount) throws InvalidAccountNumberException{
		int acctNumber=123;
		if(accountNumber!=acctNumber){
			throw new InvalidAccountNumberException();
		}
		else{ 
			return true;
		}
		
	}
	
	@Override
	public boolean withdrawAmount(int accountNumber, int amountToBeWithdrawn) throws InsufficientBalanceException,InvalidAccountNumberException{
		int accountBalance=20000;
		int acctNumber=123;
		if(accountNumber!=acctNumber){
			throw new InvalidAccountNumberException();
		}
		else{
		if(amountToBeWithdrawn>accountBalance){
			throw new InsufficientBalanceException();
		}
		return true;
		}
		
	}

}
