package com.nuaa.health.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.health.entity.Simulation;
import com.nuaa.health.repository.SimulationRepository;

@Service
public class SimulationService {
	@Autowired
	SimulationRepository simulationRepository;
	double[] eyeNormal = { 0.623567, 0.599057, 0, 0, 0.00358101, 0.00353361 };
	double[] eyeAbnormal = { 0.830315, 0.800068, 0, 0.145872, 0.00421145, 0.00368119 };
	DecimalFormat df = new DecimalFormat("#.##");

	public HashMap<String, Object> fakeData(Long uid) {
		boolean exist = simulationRepository.existsByUserId(uid);
		HashMap<String, Object> res = new HashMap<>();
		Simulation simulation = new Simulation();
		if (!exist) { // 初始化都是正常
			simulation.setUserId(uid);
			simulation.setdPressure(1);
			simulation.setsPressure(1);
			simulation.setHeartbeat(1);
			simulation.setWeight(1);
			simulation.setTemperature(1);
			simulation.setEye(1);
			simulationRepository.save(simulation);
		}
		else
			simulation = simulationRepository.findByUserId(uid);
		
		if (simulation.getEye() == 1) {
			res.put("eye", eyeNormal);
		} else
			res.put("eye", eyeAbnormal);

		if (simulation.getdPressure() == 1) {
			res.put("bp0", new Random().nextInt(30) + 90); // 正常收缩压小于120mmHg
		} else if (simulation.getdPressure() == 0)
			res.put("bp0", new Random().nextInt(30) + 60); // 偏低小于90mmHg
		else
			res.put("bp0", new Random().nextInt(30) + 140); // 偏高大于140mmHg

		if (simulation.getsPressure() == 1) {
			res.put("bp1", new Random().nextInt(20) + 60); // 正常舒张压小于80mmHg
		} else if (simulation.getsPressure() == 0)
			res.put("bp1", new Random().nextInt(30) + 30); // 偏低小于60mmHg
		else
			res.put("bp1", new Random().nextInt(20) + 90); // 偏高大于90mmHg

		if (simulation.getTemperature() == 1) {
			res.put("temperature", Double.parseDouble(df.format(new Random().nextDouble() + 36))); // 正常体温在36~37之间
		} else if (simulation.getTemperature() == 0)
			res.put("temperature", Double.parseDouble(df.format(new Random().nextDouble() + 35))); // 偏低小于36
		else
			res.put("temperature", Double.parseDouble(df.format(new Random().nextDouble() + 37))); // 偏高大于37

		if (simulation.getHeartbeat() == 1) {
			res.put("heartbeat", new Random().nextInt(40) + 60); 	// 正常心率在60~100之间
		} else if (simulation.getHeartbeat() == 0)
			res.put("heartbeat", new Random().nextInt(20) + 40); 	// 偏低小于60
		else
			res.put("heartbeat", new Random().nextInt(30) + 100); 	// 偏高大于100
		
		return res;
	}
}
