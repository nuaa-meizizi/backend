package com.nuaa.health.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.service.TokenService;
import com.nuaa.health.service.UserInfoService;
import com.nuaa.health.util.GenericJsonResult;

@RestController
@RequestMapping(value = "info")
public class UserInfoController {
	@Autowired
	private UserInfoService userinfoService;
	@Autowired
	private TokenService tokenService;
	@RequestMapping(value = "/getinfo",method=RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> getInfo(@RequestParam(value = "token", required = true) String token){
		Long userId = tokenService.getUid(token); 
		return userinfoService.getInfo(userId);
    }

    @RequestMapping(value = "/updateinfo",method=RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> updateInfo(@RequestParam(value = "token", required = true) String token,@RequestParam(value = "province", required = true) String province,@RequestParam(value = "birthday", required = true) Long birthday){
		Long userId = tokenService.getUid(token); 
		return userinfoService.updateInfo(userId,province,birthday);
    }

    @RequestMapping(value = "/saveinfo",method=RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> saveInfo(@RequestParam(value = "token", required = true) String token,@RequestParam(value = "province", required = true) String province,@RequestParam(value = "birthday", required = true) Long birthday){
		Long userId = tokenService.getUid(token); 
		return userinfoService.saveInfo(userId,province,birthday);
	}

}
