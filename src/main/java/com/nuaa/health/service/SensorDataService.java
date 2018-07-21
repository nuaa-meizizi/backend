package com.nuaa.health.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuaa.health.entity.SensorData;
import com.nuaa.health.repository.SensorDataRepository;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@Service
public class SensorDataService {
	@Autowired
	SensorDataRepository sensorDataRepository;

	public int upload(Long userId, JSONObject jsonObject) {
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		if (jsonArray == null) {
			return HResult.E_ERROR_PARAMETER; // 参数无data
		}
		ArrayList<SensorData> sensorDatas = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject tmp = jsonArray.getJSONObject(i);
			String addTime = tmp.getString("time");
			Double temperature = tmp.getDouble("temperature");
			Integer heartbeat = tmp.getInteger("heartbeat");
			Integer weight = tmp.getInteger("weight");
			Integer dPressure = tmp.getInteger("bp0");
			Integer sPressure = tmp.getInteger("bp1");
			Integer type = tmp.getInteger("type");
			boolean exist = sensorDataRepository.existsByUserIdAndAddTimeAndType(userId, addTime, type);
			if (!exist) { // 不存在才插入
				SensorData sensorData = new SensorData(userId, addTime, temperature, heartbeat, weight, dPressure,
						sPressure, type);
				sensorDatas.add(sensorData);
			}
		}
		try {
			sensorDataRepository.saveAll(sensorDatas);
		} catch (Exception e) {
			return HResult.E_DATABASE_ERROR;
		}

		return HResult.S_OK;
	}

	public GenericJsonResult<ArrayList<SensorData>> download(Long userId, JSONObject jsonObject) {
		GenericJsonResult<ArrayList<SensorData>> result = new GenericJsonResult<ArrayList<SensorData>>(HResult.S_OK);

		JSONArray jsonArray = jsonObject.getJSONArray("data");
		if (jsonArray == null) {
			result.setStatus(HResult.E_ERROR_PARAMETER);
			return result;
		}
		ArrayList<SensorData> fromRequestSensorDatas = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject tmp = jsonArray.getJSONObject(i);
			String addTime = tmp.getString("time");
			Double temperature = tmp.getDouble("temperature");
			Integer heartbeat = tmp.getInteger("heartbeat");
			Integer weight = tmp.getInteger("weight");
			Integer dPressure = tmp.getInteger("bp0");
			Integer sPressure = tmp.getInteger("bp1");
			Integer type = tmp.getInteger("type");
			SensorData sensorData = new SensorData(userId, addTime, temperature, heartbeat, weight, dPressure,
					sPressure, type);
			fromRequestSensorDatas.add(sensorData);
		}
		ArrayList<SensorData> fromDataBaseSensorDatas = sensorDataRepository.findAllByUserId(userId);
		for (SensorData sensorData : fromDataBaseSensorDatas) {
			sensorData.setId(null);
		}

		fromDataBaseSensorDatas.removeAll(fromRequestSensorDatas);
		result.setData(fromDataBaseSensorDatas);
		return result;
	}
}
