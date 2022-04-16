package com.ShopTechnological.ServicePage.reponsitory;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ShopTechnological.ServicePage.domain.MemoriesJwt;

@Transactional
public interface MemoriesJwtRepository extends CrudRepository<MemoriesJwt, Long>{
	Optional<MemoriesJwt> findByToken(String token);
}
