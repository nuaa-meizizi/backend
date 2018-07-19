package com.nuaa.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.nuaa.health.entity.SensorData;
import com.nuaa.health.repository.SensorDataRepository;
import com.nuaa.health.util.HResult;

@Service
public class SensorDataService {
	@Autowired
	SensorDataRepository sensorDataRepository;
	
	public int upload(Long userId,JSONObject jsonObject) {
		String addTime = jsonObject.getString("addtime");
		Double temperature = jsonObject.getDouble("temperature");
		Integer heartrate = jsonObject.getInteger("heartrate");
		Integer weight = jsonObject.getInteger("weight");
		Integer dPressure = jsonObject.getInteger("dPressure");
		Integer sPressure = jsonObject.getInteger("sPressure");
		Integer type = jsonObject.getInteger("type");
		SensorData sensorData = new SensorData(userId,addTime,temperature,heartrate,weight,dPressure,sPressure,type);
		try {
			sensorDataRepository.save(sensorData);
		}catch (Exception e) {
			return HResult.E_DATABASE_ERROR;
		}
		return HResult.S_OK;
	}
}
