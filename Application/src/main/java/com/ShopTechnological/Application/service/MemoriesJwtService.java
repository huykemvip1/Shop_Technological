package com.ShopTechnological.Application.service;

import com.ShopTechnological.Application.domain.MemoriesJwt;

public interface MemoriesJwtService {
	MemoriesJwt findMemory(String token);
	void update(MemoriesJwt memoriesJwt);
	void save(MemoriesJwt memoriesJwt);
	void delete(String username);
}
