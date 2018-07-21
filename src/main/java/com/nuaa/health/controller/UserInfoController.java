package com.nuaa.health.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.entity.UserInfo;
import com.nuaa.health.service.TokenService;
import com.nuaa.health.service.UserInfoService;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@RestController
@RequestMapping(value = "info")
public class UserInfoController {
	@Autowired
	private UserInfoService userinfoService;
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/getinfo", method = RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> getInfo(@RequestParam(value = "token", required = true) String token) {
		Long userId = tokenService.getUid(token);
		if (userId == null) {
			return new GenericJsonResult<Map<String, Object>>(HResult.E_TOKEN_EXPIRE_OR_NOT_EXISTENCE);
		}
		return userinfoService.getInfo(userId);
	}
	
	@RequestMapping(value = "/saveinfo", method = RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> saveInfo(@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "province", required = true) String province, @RequestParam(value = "sex", required = true) String sex,
			@RequestParam(value = "phone", required = true) String phone) {
		Long userId = tokenService.getUid(token);
		if (userId == null) {
			return new GenericJsonResult<Map<String, Object>>(HResult.E_TOKEN_EXPIRE_OR_NOT_EXISTENCE);
		}
		return userinfoService.saveInfo(userId, province, sex, phone);
	}
	
	@RequestMapping(value = "/synchinfo", method = RequestMethod.GET)
	public GenericJsonResult<UserInfo> synchronization(@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "province", required = true) String province, @RequestParam(value = "sex", required = true) String sex,
			@RequestParam(value = "phone", required = true) String phone) {
		Long userId = tokenService.getUid(token);
		if (userId == null) {
			return new GenericJsonResult<UserInfo>(HResult.E_TOKEN_EXPIRE_OR_NOT_EXISTENCE);
		}
		return userinfoService.synchInfo(userId, province, sex, phone);
	}
	
}
