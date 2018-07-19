package com.nuaa.health.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long>{
	Boolean existsByUserid(Long uid);
	Token findTokenByUserid(Long uid);
	void deleteByUserid(Long uid);
}
