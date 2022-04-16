package com.ShopTechnological.Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ShopTechnological.Application.domain.MemoriesJwt;

public interface MemoriesJwtrepository extends CrudRepository<MemoriesJwt, Long>{
	List<MemoriesJwt> findAll();
	@Modifying
	@Query(nativeQuery = true,value = "alter table memoriesjwt auto_increment=0")
	void refreshId();
}
