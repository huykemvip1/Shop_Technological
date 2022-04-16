package com.ShopTechnological.Application.security;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ShopTechnological.Application.domain.AccounSecurity;
import com.ShopTechnological.Application.domain.Account;
import com.ShopTechnological.Application.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CheckExistUsername implements UserDetailsService{
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Account> accounts=accountRepository.findAll();
		Optional<Account> account= accounts.stream()
				.filter(a -> a.getUsername().equals(username))
				.findFirst();
		if (!account.isEmpty()) {
			UserDetails userDetails=new AccounSecurity(account.get());
			return userDetails;
		}else {
			log.info("Username  is not exist");
			return null;
		}
	}
}
