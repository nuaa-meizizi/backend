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
	@Column(name = "heartbeat")
	private Integer heartbeat;
	@Column(name = "weight")
	private Integer weight;
	@Column(name = "D") // 扩张压
	private Integer dPressure;
	@Column(name = "S") // 收缩压
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public SensorData(Long userId, String addTime, Double temperature, Integer heartbeat, Integer weight,
			Integer dPressure, Integer sPressure, Integer type) {
		this.userId = userId;
		this.addTime = addTime;
		this.temperature = temperature;
		this.heartbeat = heartbeat;
		this.weight = weight;
		this.dPressure = dPressure;
		this.sPressure = sPressure;
		this.type = type;
	}

	public SensorData() {

	}

	@Override
	public String toString() {
		return "SensorData [id=" + id + ", userId=" + userId + ", addTime=" + addTime + ", temperature=" + temperature
				+ ", heartrate=" + heartbeat + ", weight=" + weight + ", dPressure=" + dPressure + ", sPressure="
				+ sPressure + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
		result = prime * result + ((dPressure == null) ? 0 : dPressure.hashCode());
		result = prime * result + ((heartbeat == null) ? 0 : heartbeat.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sPressure == null) ? 0 : sPressure.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorData other = (SensorData) obj;
		if (addTime == null) {
			if (other.addTime != null)
				return false;
		} else if (!addTime.equals(other.addTime))
			return false;
		if (dPressure == null) {
			if (other.dPressure != null)
				return false;
		} else if (!dPressure.equals(other.dPressure))
			return false;
		if (heartbeat == null) {
			if (other.heartbeat != null)
				return false;
		} else if (!heartbeat.equals(other.heartbeat))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sPressure == null) {
			if (other.sPressure != null)
				return false;
		} else if (!sPressure.equals(other.sPressure))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
}
