package com.nuaa.health.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.health.entity.User;
import com.nuaa.health.repository.UserRepository;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	TokenService tokenService;
	
	public GenericJsonResult<Map<String, Object>> login(String name,String pwd) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Map<String, Object> data = new HashMap<String, Object>();  
		User user = userRepository.findUserByNameAndPassword(name,pwd);
		if (user == null) {
			result.setStatus(HResult.E_ERROR_PASSWORD_ERROR);
		}
		else {
			String token = tokenService.updateToken(user.getId());
			data.put("id", user.getId());
			data.put("token", token);
			result.setData(data);
		}
		return result;
	}
	

	@Transactional
	public GenericJsonResult<Map<String, Object>> signUp(String name,String pwd) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Map<String, Object> data = new HashMap<String, Object>();  
		Boolean exist = userRepository.existsByName(name);
		if(exist) {
			result.setStatus(HResult.E_ERROR_USER_EXISTENCE);
		}
		else {
			User user = new User();
			user.setName(name);
			user.setPassword(pwd);
			userRepository.save(user);
			user = userRepository.findUserByNameAndPassword(name, pwd);
			String token = tokenService.addToken(user.getId());
			data.put("id", user.getId());
			data.put("token", token);
			result.setData(data);
		}
		return result;
	}
}
