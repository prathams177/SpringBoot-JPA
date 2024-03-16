package com.java.banking.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.banking.dto.*;

public interface AccountService {
	
	AccountsDto createAccount(AccountsDto accountsdto);
	
	List<AccountsDto> getAccounts();
	
	AccountsDto getAccountById(Long id);
	
	AccountsDto updateBalanceById(Long Id , double amount);
	
	AccountsDto withdrawAmount(Long Id , double amount);
	
	void deleteAccountById(Long Id);

}
