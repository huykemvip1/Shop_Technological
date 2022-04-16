package com.ShopTechnological.Application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopTechnological.Application.domain.MemoriesJwt;
import com.ShopTechnological.Application.domain.Role;
import com.ShopTechnological.Application.repository.MemoriesJwtrepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MemoriesJwtServiceImp implements MemoriesJwtService{
	@Autowired
	private MemoriesJwtrepository memoriesJwtrepository;
	@Autowired
	private SaveRole saveRole;
	// ----------- find information token
	@Override
	public MemoriesJwt findMemory(String token) {
		boolean check=false;
		List<MemoriesJwt> list=memoriesJwtrepository.findAll();
		MemoriesJwt memoriesJwt=new MemoriesJwt();
		for (MemoriesJwt m:list) {
			if (m.getToken().equals(token)) {
				check=true;
				memoriesJwt=m;
				break;
			}
		}
		if (!check) {
			return null;
		}else {
			
			return memoriesJwt;
		}
	}
	// -------- update information new token
	@Override
	public void update(MemoriesJwt memoriesJwt) {
		List<Role> roles=new ArrayList<Role>();
		for (Role r: memoriesJwt.getRoles()) {
			roles.add(saveRole.getRole(r.getName()));
		}
		Optional<MemoriesJwt> findId=memoriesJwtrepository.findAll()
				.stream()
				.filter(m -> m.getUsername().equals(m.getUsername()))
				.findFirst();
		MemoriesJwt newMemoriesJwt=findId.get();
		newMemoriesJwt.setToken(memoriesJwt.getToken());
		memoriesJwtrepository.save(newMemoriesJwt);
	}
	// ----------- Save information token
	@Override 
	public void save(MemoriesJwt memoriesJwt) {
		List<Role> roles=new ArrayList<Role>();
		for (Role r: memoriesJwt.getRoles()) {
			roles.add(saveRole.getRole(r.getName()));
		}
		memoriesJwt.setRoles(roles);
		memoriesJwtrepository.save(memoriesJwt);
	}
	// ----------- Delete information token
	@Override
	public void delete(String username) {
		List<MemoriesJwt> list=memoriesJwtrepository.findAll();
		Optional<MemoriesJwt> memoriesJwt=list.stream()
				.filter(m -> m.getUsername().equals(username))
				.findFirst();
		log.info(" ---"+memoriesJwt.isPresent());
		if (memoriesJwt.isPresent()) {
			memoriesJwtrepository.delete(memoriesJwt.get());
			memoriesJwtrepository.refreshId();
		}
	}
	
}
