package com.nuaa.health.service;

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
	private String name = "picUpload";

	public GenericJsonResult<String> getPath() {
		GenericJsonResult<String> result = new GenericJsonResult<>(HResult.S_OK);
		Boolean exist = settingsRepository.existsByName(name);
		if (exist) {
			Settings settings = settingsRepository.findByName(name);
			result.setData(settings.getValue());
		} else {
			result.setStatus(HResult.E_SETTING_EXISTENCE);
		}
		return result;
	}

	@Transactional
	public GenericJsonResult<Map<String, Object>> savePath(String path) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Settings settings = null;
		Boolean exist = settingsRepository.existsByName(name);
		if (exist) {
			settings = settingsRepository.findByName(name);
			settings.setValue(path);
		} else {
			settings = new Settings();
			settings.setName(name);
			settings.setValue(path);
		}
		try {
			settingsRepository.save(settings);
		} catch (Exception e) {
			result.setStatus(HResult.E_DATABASE_ERROR);
		}
		return result;
	}
}
