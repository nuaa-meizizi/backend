package com.nuaa.health.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class Token {
	@Id
	@Column(name = "userid")
	private Long userId;
	@Column(name = "token")
	private String token;
	@Column(name = "expire")
	private String expire;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
}
