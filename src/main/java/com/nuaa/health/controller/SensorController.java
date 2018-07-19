package com.nuaa.health.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nuaa.health.service.SensorDataService;
import com.nuaa.health.service.TokenService;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@RestController
@RequestMapping(value = "sensor")
public class SensorController {
	@Autowired
	private SensorDataService sensorDataService;
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/upload",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public GenericJsonResult<String> upload(@RequestBody JSONObject jsonParam,@RequestParam(value = "token", required = true) String token){
		GenericJsonResult<String> result = new GenericJsonResult<String>(HResult.S_OK);
		Long userId = tokenService.getUid(token);
		int ret = sensorDataService.upload(userId, jsonParam);
		if (ret != HResult.S_OK) {
			result.setStatus(ret);
		}
		return result;
	}
	
	@RequestMapping(value = "/download",method=RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> download(HttpServletRequest request) {
		return null;
	}
}