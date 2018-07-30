package com.nuaa.health.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.entity.User;
import com.nuaa.health.repository.UserRepository;
import com.nuaa.health.service.TokenService;
import com.nuaa.health.service.UserService;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@RestController
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public GenericJsonResult<Map<String, Object>> signUp(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "password", required = true) String password) {
		return userService.signUp(name, password);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public GenericJsonResult<Map<String, Object>> login(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "password", required = true) String password) {
		return userService.login(name, password);
	}
	
	@RequestMapping(value = "/getalluser", method = RequestMethod.GET)
	public GenericJsonResult<ArrayList<Map<String, Object>>> getAllUser(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		return userService.getAllUser();
	}
	
	@RequestMapping(value = "/getToken", method = RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> getToken(@RequestParam(value = "uid", required = true) Long uid) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		String token = tokenService.updateToken(uid);
		Map<String, Object> data = new HashMap<String, Object>();  
		data.put("id", uid);
		data.put("token", token);
		User user = userRepository.findUserById(uid);
		data.put("name", user.getName());
		data.put("password", user.getPassword());
		result.setData(data);
		return result;
	}
}
