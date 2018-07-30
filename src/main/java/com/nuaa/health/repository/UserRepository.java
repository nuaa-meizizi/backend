package com.nuaa.health.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findUserByNameAndPassword(String name,String pasword);
	User findUserById(Long uid);
	Boolean existsByName(String name);
}
