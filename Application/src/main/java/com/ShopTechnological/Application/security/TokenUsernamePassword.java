package com.ShopTechnological.Application.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class TokenUsernamePassword extends UsernamePasswordAuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenUsernamePassword(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		
	}
	public TokenUsernamePassword(Object principal, Object credentials) {
		super(principal, credentials);
		
	}

}
