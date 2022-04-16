package com.ShopTechnological.Application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopTechnological.Application.domain.Role;
import com.ShopTechnological.Application.repository.RoleRepository;

@Service
public class SaveRole {
	@Autowired
	private RoleRepository roleRepository;
	
	public Role getRole(String name) {
		Role role=new Role();
		for (Role r: roleRepository.findAll()) {
			if (r.getName().equals(name)) {
				role=r;
				break;
			}
		}
		return role;
	}
	
}
