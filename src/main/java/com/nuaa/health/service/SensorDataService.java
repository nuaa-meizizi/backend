package com.nuaa.health.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuaa.health.entity.SensorData;
import com.nuaa.health.repository.SensorDataRepository;
import com.nuaa.health.util.HResult;

@Service
public class SensorDataService {
	@Autowired
	SensorDataRepository sensorDataRepository;
	
	public int upload(Long userId,JSONObject jsonObject) {
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		if (jsonArray == null) {
			return HResult.E_PASSWORD_ERROR;				//参数无data
		}
		ArrayList<SensorData> sensorDatas = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject tmp = jsonArray.getJSONObject(i);
			String addTime = tmp.getString("addtime");
			Double temperature = tmp.getDouble("temperature");
			Integer heartrate = tmp.getInteger("heartrate");
			Integer weight = tmp.getInteger("weight");
			Integer dPressure = tmp.getInteger("dPressure");
			Integer sPressure = tmp.getInteger("sPressure");
			Integer type = tmp.getInteger("type");
			boolean exist = sensorDataRepository.existsByUserIdAndAddTimeAndType(userId, addTime, type);
			if (!exist) {		//不存在才插入
				SensorData sensorData = new SensorData(userId,addTime,temperature,heartrate,weight,dPressure,sPressure,type);
				sensorDatas.add(sensorData);
			}
		}
		try {
			sensorDataRepository.saveAll(sensorDatas);
		}catch (Exception e) {
			return HResult.E_DATABASE_ERROR;
		}

		return HResult.S_OK;
	}
}
