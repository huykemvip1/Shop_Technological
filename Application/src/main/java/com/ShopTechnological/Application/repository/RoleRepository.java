package com.ShopTechnological.Application.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.ShopTechnological.Application.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	List<Role> findAll();
	
}
