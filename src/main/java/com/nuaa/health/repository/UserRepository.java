package com.nuaa.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>,JpaRepository<User, Long> {
	User findUserByNameAndPassword(String name,String pasword);
	Boolean existsByName(String name);
}
