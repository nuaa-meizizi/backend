package com.nuaa.health.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.UserInfo;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
    Boolean existsByUserid(Long userid);
    UserInfo findByUserid(Long userid);
}
