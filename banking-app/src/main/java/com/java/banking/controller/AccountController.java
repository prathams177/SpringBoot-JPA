package com.java.banking.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.banking.dto.AccountsDto;
import com.java.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    // add accounts rest api
    @PostMapping
    public ResponseEntity<AccountsDto> addAccount(@RequestBody AccountsDto accountsDto) {
        return new ResponseEntity<>(accountService.createAccount(accountsDto), HttpStatus.CREATED);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<AccountsDto>> getAllAccounts(){
		return new ResponseEntity<List<AccountsDto>>(accountService.getAccounts() ,HttpStatus.OK );
    	
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountsDto> getAccountById(@PathVariable Long id){
    	return new ResponseEntity<AccountsDto>(accountService.getAccountById(id), HttpStatus.OK);
    }
    
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountsDto> updateBalance(@PathVariable 
    		Long id ,@RequestBody Map<String, Double> request){
    	Double amount = request.get("amount");;
    	
    	return new ResponseEntity<AccountsDto>(accountService.updateBalanceById(id, amount),HttpStatus.OK);
    }
    
    @PutMapping("/{Id}/withdraw")
    public ResponseEntity<AccountsDto> getWithdraw(@PathVariable Long Id ,@RequestBody  Map<String, Double> request){
    	Double amount = request.get("amount");
    	AccountsDto accountsDto = accountService.withdrawAmount(Id, amount);
    	System.out.println("object = "+ accountsDto);
    	return  ResponseEntity.ok(accountsDto);
    }
    
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long Id){
    	accountService.deleteAccountById(Id);
		return new ResponseEntity<String>("Account deleted successfully",HttpStatus.OK);
    }
}
