package com.nuaa.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.health.entity.Device;
import com.nuaa.health.repository.DeviceRepository;

@Service
public class DeviceService {
	@Autowired
	DeviceRepository deviceRepository;
	private final int liveTime = 5 * 60 * 1000; // 在5min有活动

	public void update(String imei) {
		Device device = new Device();
		device.setImei(imei);
		device.setUpdatedTime(System.currentTimeMillis());
		deviceRepository.save(device);
	}

	public int countLiveNum() {
		Long currentTime = System.currentTimeMillis();
		Long requireTime = currentTime - liveTime;
		int count = deviceRepository.countLiveNum(requireTime);
		return count;
	}
}
