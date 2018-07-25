package com.nuaa.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Simulation {
	@Id
	@Column(name = "userid")
	private Long userId;
	@Column(name = "temperature")
	private Integer temperature;
	@Column(name = "heartbeat")
	private Integer heartbeat;
	@Column(name = "weight")
	private Integer weight;
	@Column(name = "D")
	private Integer dPressure;
	@Column(name = "S")
	private Integer sPressure;
	@Column(name = "eye")
	private Integer eye;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Integer getTemperature() {
		return temperature;
	}
	
	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
	
	public Integer getHeartbeat() {
		return heartbeat;
	}
	
	public void setHeartbeat(Integer heartbeat) {
		this.heartbeat = heartbeat;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Integer getdPressure() {
		return dPressure;
	}
	
	public void setdPressure(Integer dPressure) {
		this.dPressure = dPressure;
	}
	
	public Integer getsPressure() {
		return sPressure;
	}
	
	public void setsPressure(Integer sPressure) {
		this.sPressure = sPressure;
	}
	
	public Integer getEye() {
		return eye;
	}
	
	public void setEye(Integer eye) {
		this.eye = eye;
	}
	
	@Override
	public String toString() {
		return "Simulation [userId=" + userId + ", temperature=" + temperature + ", heartbeat=" + heartbeat + ", weight=" + weight + ", dPressure=" + dPressure + ", sPressure="
				+ sPressure + ", eye=" + eye + "]";
	}
	
}
