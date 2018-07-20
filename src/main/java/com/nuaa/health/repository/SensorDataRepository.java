package com.nuaa.health.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.SensorData;

@Repository
public interface SensorDataRepository extends CrudRepository<SensorData, Long> {
	Boolean existsByUserIdAndAddTimeAndType(Long uid,String addTime,Integer type);
}
