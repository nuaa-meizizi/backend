package com.nuaa.health.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.service.UserInfoService;
import com.nuaa.health.util.GenericJsonResult;

@RestController
@RequestMapping(value = "info")
public class UserInfoController {
	@Autowired
	private UserInfoService userinfoService;
	@RequestMapping(value = "/getinfo",method=RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> getinfo(@RequestParam(value = "userid", required = true) Long userid){
		 return userinfoService.getinfo(userid);
    }

    @RequestMapping(value = "/updateinfo",method=RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> updateinfo(@RequestParam(value = "userid", required = true) Long userid,@RequestParam(value = "province", required = true) String province,@RequestParam(value = "birthday", required = true) Long birthday){
		 return userinfoService.updateinfo(userid,province,birthday);
    }

    @RequestMapping(value = "/saveinfo",method=RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> saveinfo(@RequestParam(value = "userid", required = true) Long userid,@RequestParam(value = "province", required = true) String province,@RequestParam(value = "birthday", required = true) Long birthday){
		 return userinfoService.saveinfo(userid,province,birthday);
	}

}
