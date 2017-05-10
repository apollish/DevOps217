package com.capgemini.repository;

import com.capgemini.beans.Accounts;

public interface AccountRepository {
	
	boolean save(Accounts account);
	
	Accounts searchAccount(int accountNumber);

}
