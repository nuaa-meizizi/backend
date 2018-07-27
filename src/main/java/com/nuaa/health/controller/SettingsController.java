package com.nuaa.health.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.nuaa.health.service.SettingsService;
import com.nuaa.health.util.GenericJsonResult;

@RestController
@RequestMapping(value = "settings")
public class SettingsController {
	@Autowired
	private SettingsService settingsService;

	@RequestMapping(value = "/getpath", method = RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> getPath(@RequestParam(value = "id", required = true) Integer id) {
		return settingsService.getPath(id);
	}

	@RequestMapping(value = "/savepath", method = RequestMethod.GET)
	public GenericJsonResult<Map<String, Object>> savePath(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "path", required = true) String path) {
		return settingsService.savePath(id,path);
	}

}
