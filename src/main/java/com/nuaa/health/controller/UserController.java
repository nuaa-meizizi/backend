package com.nuaa.health.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.service.UserService;
import com.nuaa.health.util.GenericJsonResult;

@RestController
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/signup",method=RequestMethod.POST)
	public GenericJsonResult<Map<String, Object>> signUp(@RequestParam(value = "name", required = true) String name,@RequestParam(value = "password", required = true) String password){
		 return userService.signUp(name,password);
	}
	
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public GenericJsonResult<Map<String, Object>> login(@RequestParam(value = "name", required = true) String name,@RequestParam(value = "password", required = true) String password) {
		return userService.login(name,password);
	}
	
	@RequestMapping(value = "/getalluser",method=RequestMethod.GET)
	public GenericJsonResult<ArrayList<Map<String, Object>>> getAllUser() {
		return userService.getAllUser();
	}
}
