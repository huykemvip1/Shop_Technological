package com.ShopTechnological.Application.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopTechnological.Application.domain.Account;
import com.ShopTechnological.Application.domain.Role;
import com.ShopTechnological.Application.exception.ExceptionConditionSave;
import com.ShopTechnological.Application.exception.UsernameNotFound;
import com.ShopTechnological.Application.exception.UsernameRoleAlreadyExist;
import com.ShopTechnological.Application.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
// -------- Resource manager controller
public class AccountServiceImp implements AccountService{
	protected List<Account> accounts=new ArrayList<Account>();
	protected List<Role> roles=new ArrayList<Role>();
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private SaveRole saveRole;
	//-------  Save list role
	protected void roles(Account account){
		for (Role r : account.getRoles()) {
			roles.add(saveRole.getRole(r.getName()));
		}
	}
	// ----------------    find all account
	protected void accounts(){
		 accounts=accountRepository.findAll();
	}
	// ------------ Add Account
	@Override
	public void addAccount(Account account) throws UsernameRoleAlreadyExist  {
		roles(account);
		accounts();
		log.info("Add from manager : "+account);
		List<Account> findAccount=accounts.stream()
				.filter(a -> a.getUsername().equals(account.getUsername())).toList();
		account.setRoles(roles);
		if (findAccount.size()<1) {
			accountRepository.refreshId();
		    accountRepository.save(account);
		}else {
				 throw new UsernameRoleAlreadyExist("Username is already exist");
		}
	}
	//------------------- Update Account
	@Override
	public void updateAccount(Account account,String username) throws ExceptionConditionSave, UsernameNotFound{
		long id = 0;
		boolean checkPassword=true;
		boolean checkEmail=true;
		boolean checkPhone=true;
		boolean checkRole=true;
		accounts();
		//------------
		for (Account ac: accounts) {
			if (account.getUsername().equals(ac.getUsername())) {
				id=ac.getId();
				break;
			}
		}
		//------------------------
		if (id==0) {
			throw new UsernameNotFound("Username is not found");
		}
		Account oldAccount=accountRepository.findById(id).get();
		// full name update
		
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
		if (account.getRoles().size() >=1) {
			roles.removeAll(roles);
			roles(account);
			log.info(" ------ "+roles);
			oldAccount.setRoles(roles);
		}else {
			checkRole=false;
		}
		// condition save
		if (checkRole!=false && checkEmail != false && checkPassword != false 
				&& checkPhone != false) {
			    
			    accountRepository.save(oldAccount);
				log.info("Update success from manager : "+oldAccount);
				
		}else {
			throw new ExceptionConditionSave("Empty Role , Password , Phone , Email");
		}
	}
	@Override
	public void deleteAccount(String username) throws UsernameNotFound {
		accounts();
		Account account=(Account) accounts.stream()
				.filter(a -> a.getUsername().equals(username));
		if (account != null) {
			accountRepository.delete(account);
		}else {
			throw new UsernameNotFound("Username is not found");
		}
	}
	// ------------------- Find 1 account
	@Override
	public Account getAccountOne(String username) throws UsernameNotFound {
		accounts();
		Account account=(Account) accounts.stream()
				.filter(a -> a.getUsername().equals(username));
		if (account != null) {
			return account;
		}else {
			throw new UsernameNotFound("Username is not found");
		}
	}
	// ---------- get all Account
	@Override
	public List<Account> getAll() {
		accounts();
		return accounts;
	}
	
}
