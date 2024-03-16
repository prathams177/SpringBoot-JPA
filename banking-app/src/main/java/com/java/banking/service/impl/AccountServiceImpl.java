package com.java.banking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.java.banking.dto.AccountsDto;
import com.java.banking.entity.Accounts;
import com.java.banking.mapper.AccountMapper;
import com.java.banking.repository.AccountRepository;
import com.java.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountsDto createAccount(AccountsDto accountsDto) {
    	/*
        // Convert DTO to entity
        Accounts accountEntity = new Accounts();
        accountEntity.setAccountHolderName(accountsDto.getAccountHolderName());
        accountEntity.setBalance(accountsDto.getBalance());

        // Save the entity to the database using the repository
        Accounts savedAccount = accountRepository.save(accountEntity);

        // Convert the saved entity back to DTO and return
        AccountsDto savedAccountDto = new AccountsDto();
        savedAccountDto.setId(savedAccount.getId());
        savedAccountDto.setAccountHolderName(savedAccount.getAccountHolderName());
        savedAccountDto.setBalance(savedAccount.getBalance());  
        */
    	
    	Accounts accountEntity=AccountMapper.mapToAccounts(accountsDto);
    	Accounts savedAccount = accountRepository.save(accountEntity);
    	AccountsDto finalDto = AccountMapper.mapToDto(savedAccount);
    	

        return finalDto;
    }

	@Override
	public List<AccountsDto> getAccounts() {
		
		List<Accounts>fetchAccounts=accountRepository.findAll();
		
		return fetchAccounts.stream().map(AccountMapper::mapToDto).collect(Collectors.toList());
	}

	@Override
	public AccountsDto getAccountById(Long id) {
		Accounts accounts= accountRepository
		.findById(id)
		.orElseThrow(()-> new RuntimeException("Account doesnot exist"));
		return AccountMapper.mapToDto(accounts);
	}

	@Override
	public AccountsDto updateBalanceById(Long Id, double amount) {
		
		Accounts accounts= accountRepository
		.findById(Id)
		.orElseThrow(()-> new RuntimeException("Account doesnot exist"));
		
		double updatedBalance = accounts.getBalance()+ amount;
		
		//now we have to save the updated balance to the DB
		accounts.setBalance(updatedBalance);
		Accounts newUpdatedAccounts =  accountRepository.save(accounts);
		
		
		
		return AccountMapper.mapToDto(newUpdatedAccounts);
	}

	@Override
	public AccountsDto withdrawAmount(Long Id , double amount) {
		Accounts accounts= accountRepository
				.findById(Id)
				.orElseThrow(()-> new RuntimeException("Account doesnot exist"));
		
		if(accounts.getBalance()< amount ) {
			throw new RuntimeException("Account Balance is low");	
		}
		double total = accounts.getBalance()- amount;
		accounts.setBalance(total);
		Accounts newUpdatedAccounts =  accountRepository.save(accounts);
		return AccountMapper.mapToDto(newUpdatedAccounts);
		
	}

	@Override
	public void deleteAccountById(Long Id) {
		Accounts accounts= accountRepository
				.findById(Id)
				.orElseThrow(()-> new RuntimeException("Account doesnot exist"));
		accountRepository.deleteById(Id);
		
		
	}




}
