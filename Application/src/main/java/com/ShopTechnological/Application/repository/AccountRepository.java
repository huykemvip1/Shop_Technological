package com.ShopTechnological.Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.ShopTechnological.Application.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	List<Account> findAll();
	@Modifying
	@Query(nativeQuery = true,value = "alter table account auto_increment=0")
	void refreshId();
}
