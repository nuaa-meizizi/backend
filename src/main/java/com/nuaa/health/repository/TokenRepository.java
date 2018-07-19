package com.nuaa.health.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long>{
	Boolean existsByUserId(Long uid);
	Token findTokenByUserId(Long uid);
	Token findTokenByToken(String token);
	void deleteByUserId(Long uid);
}
