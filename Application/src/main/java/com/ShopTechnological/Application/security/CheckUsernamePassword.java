package com.ShopTechnological.Application.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.ShopTechnological.Application.domain.Account;
import com.ShopTechnological.Application.repository.AccountRepository;

@Component
public class CheckUsernamePassword implements AuthenticationProvider{
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username =authentication.getName();
		String password =authentication.getCredentials().toString();
		Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
		List<Account> accounts=accountRepository.findAll();
		Optional<Account> account= accounts.stream()
				.filter(r -> r.getUsername().equals(username))
				.findFirst();
		if (password.equals(account.get().getPassword())) {
			return new TokenUsernamePassword(username,password,authorities);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(TokenUsernamePassword.class);
	}
	
}
