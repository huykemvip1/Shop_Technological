package com.ShopTechnological.Application.service;

import java.util.List;

import com.ShopTechnological.Application.domain.Account;
import com.ShopTechnological.Application.exception.ExceptionConditionSave;
import com.ShopTechnological.Application.exception.UsernameNotFound;
import com.ShopTechnological.Application.exception.UsernameRoleAlreadyExist;

public interface AccountService {
	// Dua tat ca cac Account va Role
	List<Account> getAll();
	// Them Account va Role
	void addAccount(Account account) throws UsernameRoleAlreadyExist;
	// Xoa Account va Role
	void deleteAccount(String username) throws UsernameNotFound;
	// Tim mot account
	Account getAccountOne(String username) throws UsernameNotFound;
	// Cap nhat Account va Role
	void updateAccount(Account account,String username) 
			throws ExceptionConditionSave,UsernameNotFound;
}
