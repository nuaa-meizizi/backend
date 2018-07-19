package com.nuaa.health.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorDataServiceTest {
	@Autowired
	SensorDataService sensorDataService;
	
	@Test
	public void testUpload() {
		Long userId = 1L;
		JSONObject jsonObject = JSONObject.parseObject("{\r\n" + 
				"    \"addtime\":\"121212\",\r\n" + 
				"    \"temperature\":38.4,\r\n" + 
				"    \"heartrate\":78,\r\n" + 
				"    \"weight\":55,\r\n" + 
				"    \"dPressure\":78,\r\n" + 
				"    \"sPressure\":89,\r\n" + 
				"    \"type\":1\r\n" + 
				"}");
		sensorDataService.upload(userId, jsonObject);
	}

}
