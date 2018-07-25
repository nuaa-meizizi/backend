package com.nuaa.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.health.entity.Device;
import com.nuaa.health.repository.DeviceRepository;

@Service
public class DeviceService {
	@Autowired
	DeviceRepository deviceRepository;
	public void update(String imei) {
		Device device = new Device();
		device.setImei(imei);
		device.setUpdatedTime(Long.toString(System.currentTimeMillis()));
		deviceRepository.save(device);
	}
}
