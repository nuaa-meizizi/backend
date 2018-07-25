package com.nuaa.health.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {

}
