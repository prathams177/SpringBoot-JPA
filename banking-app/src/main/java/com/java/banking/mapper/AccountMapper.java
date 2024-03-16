package com.java.banking.mapper;

import com.java.banking.dto.AccountsDto;
import com.java.banking.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component // Add @Component annotation to make it a Spring bean
@Mapper
public class AccountMapper {
	
	public static Accounts mapToAccounts(AccountsDto accountsDto) {
		Accounts accounts = new Accounts();
		accounts.setId(accountsDto.getId());
		accounts.setAccountHolderName(accountsDto.getAccountHolderName());
		accounts.setBalance(accountsDto.getBalance());
		return accounts;
		
	}
	
	public static AccountsDto mapToDto(Accounts accounts) {
		AccountsDto accountsDto = new AccountsDto();
		accountsDto.setId(accounts.getId());
		accountsDto.setAccountHolderName(accounts.getAccountHolderName());
		accountsDto.setBalance(accounts.getBalance());
		return accountsDto;
		
	}


}
