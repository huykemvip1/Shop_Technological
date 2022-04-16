package com.ShopTechnological.Application.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.ShopTechnological.Application.domain.Account;
import com.ShopTechnological.Application.exception.ExceptionConditionSave;
import com.ShopTechnological.Application.exception.UsernameNotFound;
import com.ShopTechnological.Application.exception.UsernameRoleAlreadyExist;
import com.ShopTechnological.Application.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ShopTechnological/manager")
@Slf4j
public class ManagerController {
	@Autowired
	private AccountService accountService;
	// ------------- Get All
	@GetMapping("/all")
	public ResponseEntity<List<Account>> getAll() {
		return ResponseEntity.ok().body(accountService.getAll());
	}
	//------------------ find 1 account
	@GetMapping("/details/{username}")
	public ResponseEntity<Account> getOne(@PathVariable String username) throws UsernameNotFound {
		Account account=accountService.getAccountOne(username);
		return ResponseEntity.ok().body(account);
	}
	
	//-------- Resource add Account
	@PostMapping(value = "/add",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseEntity<?> addAccount(@RequestBody Account account) 
			throws UsernameRoleAlreadyExist{
		log.info("Update : "+account);
		accountService.addAccount(account);
		return ResponseEntity.ok().build();
	}
	//-------- Resource update Account
	@PutMapping(value = "/update/{username}",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseEntity<Account> updateAccount(@PathVariable String username,
			@RequestBody Account account) throws ExceptionConditionSave, UsernameNotFound{
		accountService.updateAccount(account,username);
		return ResponseEntity.ok().build();
	}
	// --------- Delete 1 account
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<Account> deleteAccount(@PathVariable("username") String username,
			@RequestBody Account account) throws UsernameNotFound{
		accountService.deleteAccount(username);
		return ResponseEntity.ok().build();
	}
}
