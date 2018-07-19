package com.nuaa.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensor")
public class SensorData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "userid")
	private Long userId;
	@Column(name = "addtime")
	private String addTime;
	@Column(name = "temperature")
	private Double temperature;
	@Column(name = "heartrate")
	private Integer heartrate;
	@Column(name = "weight")
	private Integer weight;
	@Column(name = "D")				//扩张压
	private Integer dPressure;
	@Column(name = "S")				//收缩压
	private Integer sPressure;
	@Column(name = "type")
	private Integer type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Integer getHeartrate() {
		return heartrate;
	}
	public void setHeartrate(Integer heartrate) {
		this.heartrate = heartrate;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public SensorData(Long userId, String addTime, Double temperature, Integer heartrate, Integer weight,
			Integer dPressure, Integer sPressure, Integer type) {
		this.userId = userId;
		this.addTime = addTime;
		this.temperature = temperature;
		this.heartrate = heartrate;
		this.weight = weight;
		this.dPressure = dPressure;
		this.sPressure = sPressure;
		this.type = type;
	}
	public SensorData() {
		
	}
	
}
