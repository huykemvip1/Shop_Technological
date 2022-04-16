package com.ShopTechnological.Application.service;

import com.ShopTechnological.Application.domain.Account;
import com.ShopTechnological.Application.exception.ExceptionConditionSave;
import com.ShopTechnological.Application.exception.UsernameNotFound;
import com.ShopTechnological.Application.exception.UsernameRoleAlreadyExist;

public interface AccountUser {
	void addAccount(Account account) throws UsernameRoleAlreadyExist;
	void updateAccount(Account account) throws UsernameNotFound,ExceptionConditionSave;
}
