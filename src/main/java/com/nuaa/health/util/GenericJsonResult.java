package com.nuaa.health.util;

import java.util.HashMap;

public class GenericJsonResult<T> {
	private int status;
	private String message;
	private T data;
	private static HashMap<Integer, String> s_messageMapping;

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		this.message = getMessage(status);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public GenericJsonResult(int status) {
		this.status = status;
		this.message = getMessage(status);
	}
	
	public GenericJsonResult(int status,T data) {
		this.status = status;
		this.data = data;
		this.message = getMessage(status);
	}
	
	
	static {
		s_messageMapping = new HashMap<Integer, String>();
		s_messageMapping.put(HResult.S_OK, "成功");
		s_messageMapping.put(HResult.E_UNKNOWN, "未知错误，请稍候重试");
		s_messageMapping.put(HResult.E_FILE_EXCEPTION, "文件错误");
		s_messageMapping.put(HResult.E_ERROR_PARAMETER, "参数错误");
		s_messageMapping.put(HResult.E_USER_EXISTENCE, "用户已存在");
		s_messageMapping.put(HResult.E_PASSWORD_ERROR, "用户名或密码错误");
		s_messageMapping.put(HResult.E_DATABASE_ERROR, "操作数据库出错");
		s_messageMapping.put(HResult.E_USER_NOTEXIST, "用户不存在");
		s_messageMapping.put(HResult.E_USER_INFO_EXISTENCE, "用户信息已存在");
		s_messageMapping.put(HResult.E_UPLOAD_FAIL, "上传失败");
		s_messageMapping.put(HResult.E_UPLOAD_FILE_EMPTY, "文件为空");
		s_messageMapping.put(HResult.E_TOKEN_EXPIRE_OR_NOT_EXISTENCE, "登陆信息错误");
		s_messageMapping.put(HResult.E_SETTING_EXISTENCE, "该配置项不存在");
	}
	
	private String getMessage(int status) {
		String message = s_messageMapping.get(status);
		if (message == null) {
			message = s_messageMapping.get(HResult.E_UNKNOWN);
		}
		return message;
	}
}
