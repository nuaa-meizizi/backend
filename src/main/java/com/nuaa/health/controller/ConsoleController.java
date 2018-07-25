package com.nuaa.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.entity.Simulation;
import com.nuaa.health.repository.SimulationRepository;
import com.nuaa.health.service.DeviceService;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@RestController
@RequestMapping(value = "console")
public class ConsoleController {
	@Autowired
	DeviceService deviceService;
	@Autowired
	SimulationRepository simulationRepository;
	
	@RequestMapping(value = "/iamlive", method = RequestMethod.GET)
	public GenericJsonResult<String> iAmLive(@RequestParam(value = "imei", required = true) String imei) {
		GenericJsonResult<String> res = new GenericJsonResult<>(HResult.S_OK);
		deviceService.update(imei);
		return res;
	}
	
	@RequestMapping(value = "/livenum", method = RequestMethod.GET)
	public GenericJsonResult<Integer> liveNum() {
		GenericJsonResult<Integer> res = new GenericJsonResult<>(HResult.S_OK);
		int count = deviceService.countLiveNum();
		res.setData(count);
		return res;
	}
	
	@RequestMapping(value = "/setStatus", method = RequestMethod.POST)
	public GenericJsonResult<String> fakeData(@RequestBody Simulation simulation) {
		GenericJsonResult<String> res = new GenericJsonResult<>(HResult.S_OK);
		try {
			simulationRepository.save(simulation);
		} catch (Exception e) {
			res.setStatus(HResult.E_DATABASE_ERROR);
			res.setData(e.toString());
		}
		return res;
	}
}
