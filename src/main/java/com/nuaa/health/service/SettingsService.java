package com.nuaa.health.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.health.entity.Settings;
import com.nuaa.health.repository.SettingsRepository;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@Service
public class SettingsService {
	@Autowired
	SettingsRepository settingsRepository;

	public GenericJsonResult<Map<String, Object>> getPath(Integer id) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Map<String, Object> data = new HashMap<String, Object>();
		Boolean exist = settingsRepository.existsByid(id);
		if (exist) {
			Settings settings = new Settings();
			settings = settingsRepository.findByid(id);
			data.put("id", settings.getId());
			data.put("path", settings.getPath());
			result.setData(data);
		} else {
			result.setStatus(HResult.E_USER_NOTEXIST);
		}
		return result;
	}

	@Transactional
	public GenericJsonResult<Map<String, Object>> savePath(Integer id, String path) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Settings settings = new Settings();
		settings.setId(id);
		settings.setPath(path);
		settingsRepository.save(settings);
		return result;
	}
}
