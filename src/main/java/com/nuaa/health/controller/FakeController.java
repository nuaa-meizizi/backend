package com.nuaa.health.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.service.SimulationService;
import com.nuaa.health.service.TokenService;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@RestController
@RequestMapping(value = "fake")
public class FakeController {
	@Autowired
	SimulationService simulationService;
	@Autowired
	TokenService tokenService;

	@RequestMapping(value = "/fakedata", method = RequestMethod.GET)
	public GenericJsonResult<HashMap<String, Object>> fakeData(
			@RequestParam(value = "token", required = true) String token) {
		GenericJsonResult<HashMap<String, Object>> res = new GenericJsonResult<>(HResult.S_OK);
		Long uid = tokenService.getUid(token);
		if (uid == null) {
			if (token.equals("anony")) {		//匿名用户默认用uid控制
				res.setData(simulationService.fakeData(1L));
			}
			else
				res.setStatus(HResult.E_TOKEN_EXPIRE_OR_NOT_EXISTENCE);
		} else {
			res.setData(simulationService.fakeData(uid));
		}
		return res;
	}
}
