package com.ShopTechnological.Application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ShopTechnological.Application.security.CheckUsernamePassword;
import com.ShopTechnological.Application.security.FilterEntryLogin;
import com.ShopTechnological.Application.security.FilterEntryResource;

@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	private CheckUsernamePassword checkUsernamePassword;
	@Autowired
	private FilterEntryLogin filterEntryLogin;
	@Autowired
	private FilterEntryResource filterEntryResource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(checkUsernamePassword);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(filterEntryLogin, BasicAuthenticationFilter.class);
		http.addFilterAfter(filterEntryResource, BasicAuthenticationFilter.class);
		http.cors();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/manager/**").hasRole("manager");
		http.authorizeHttpRequests().anyRequest().permitAll();	
	}
	
}
