package com.nuaa.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {
	@Id
	@Column(name = "userid")
	private Long userid;
	@Column(name = "province")
	private String province;
	@Column(name = "sex")
	private Integer sex;
	
	public Long getUserid() {
		return userid;
	}
	
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvine(String province) {
		this.province = province;
	}
	
	public Integer getSex() {
		return sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
