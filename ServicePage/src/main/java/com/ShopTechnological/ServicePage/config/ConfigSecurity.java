package com.ShopTechnological.ServicePage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ShopTechnological.ServicePage.Security.FilterEntry;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
	private final FilterEntry filterEntry;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(filterEntry, BasicAuthenticationFilter.class);
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/ShopTechnological/have/**").hasRole("manager");
	}
}
