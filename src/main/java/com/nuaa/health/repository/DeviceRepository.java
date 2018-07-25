package com.nuaa.health.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {
	@Query(value = "SELECT COUNT(*) FROM device WHERE updated_time > ?1", nativeQuery = true)
	Integer countLiveNum(Long requireTime);
}
