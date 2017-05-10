package com.capgemini.service;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.beans.Accounts;

public interface AccountService 
{

Accounts createAccount(int accountNumber, int amount) throws InsufficientInitialAmountException;
boolean amountDeposit(int accountNumber, int amount) throws InvalidAccountNumberException;
boolean withdrawAmount(int accountNumber, int amount) throws InsufficientBalanceException,InvalidAccountNumberException;

}