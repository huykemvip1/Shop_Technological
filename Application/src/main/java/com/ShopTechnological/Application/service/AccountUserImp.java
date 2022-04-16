package com.ShopTechnological.Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ShopTechnological.Application.domain.Account;
import com.ShopTechnological.Application.exception.ExceptionConditionSave;
import com.ShopTechnological.Application.exception.UsernameNotFound;
import com.ShopTechnological.Application.exception.UsernameRoleAlreadyExist;
import com.ShopTechnological.Application.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountUserImp implements AccountUser{
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public void addAccount(Account account) throws UsernameRoleAlreadyExist {
		List<Account> accounts=accountRepository.findAll();
		Optional<Account> ac=accounts.stream()
				.filter(a -> a.getUsername().equals(account.getUsername()))
				.findFirst();
		if (ac.isEmpty()) {
			accountRepository.refreshId();
			accountRepository.save(account);
		}else {
			throw new UsernameNotFoundException("Username is already exist");
		}
	}

	@Override
	public void updateAccount(Account account) throws UsernameNotFound, ExceptionConditionSave {
		long id = 0;
		boolean checkPassword=true;
		boolean checkEmail=true;
		boolean checkPhone=true;
		List<Account> accounts=accountRepository.findAll();
		for (Account ac: accounts) {
			if (account.getUsername().equals(ac.getUsername())) {
				id=ac.getId();
				break;
			}
		}
		Account oldAccount=accountRepository.findById(id).get();
		if (id==0) {
			throw new UsernameNotFound("Username is not found");
		}
		oldAccount.setFullname(account.getFullname());
		
		// birthday update
			
		oldAccount.setBirthday(account.getBirthday());
			
		// sex update
			
		oldAccount.setSex(account.getSex());
			
		// address update
			
		oldAccount.setAddress(account.getAddress());
			
		// Password update
		if (account.getPassword() != null && !account.getPassword().equals("")) {
			oldAccount.setPassword(account.getPassword());
		}else {
			checkPassword=false;
		}
		// Email update
		if (account.getEmail() != null && !account.getEmail().equals("")) {
			oldAccount.setEmail(account.getEmail());
		}else {
			checkEmail=false;
		}
		// Phone number update
		if (account.getPhonenumber() != null && !account.getPhonenumber().equals("")) {
			oldAccount.setPhonenumber(account.getPhonenumber());
		}else {
			checkPhone=false;
		}
		if ( checkEmail != false && checkPassword != false 
				&& checkPhone != false) {
			    
			    accountRepository.save(oldAccount);
				log.info("Update success from manager : "+oldAccount);
				
		}else {
			throw new ExceptionConditionSave("Empty Role , Password , Phone , Email");
		}
	}

}
