package com.nuaa.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.service.DeviceService;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@RestController
@RequestMapping(value = "console")
public class ConsoleController {
	@Autowired DeviceService deviceService;
	
	@RequestMapping(value = "/iamlive", method = RequestMethod.GET)
	public GenericJsonResult<String> iAmLive(@RequestParam(value = "imei", required = true) String imei) {
		GenericJsonResult<String> res = new GenericJsonResult<>(HResult.S_OK);
		deviceService.update(imei);
		return res;
	}
}
