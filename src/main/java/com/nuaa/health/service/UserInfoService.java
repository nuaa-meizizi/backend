package com.nuaa.health.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.health.entity.UserInfo;
import com.nuaa.health.repository.UserInfoRepository;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

@Service
public class UserInfoService {
	@Autowired
	UserInfoRepository userinfoRepository;
	
	public GenericJsonResult<Map<String, Object>> getInfo(Long userid) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Map<String, Object> data = new HashMap<String, Object>();
		Boolean exist = userinfoRepository.existsByUserid(userid);
		if (exist) {
			UserInfo userinfo = new UserInfo();
			userinfo = userinfoRepository.findByUserid(userid);
			data.put("userid", userinfo.getUserid());
			data.put("province", userinfo.getProvince());
			data.put("sex", userinfo.getSex());
			data.put("phone", userinfo.getPhone());
			result.setData(data);
		} else {
			result.setStatus(HResult.E_USER_NOTEXIST);
		}
		return result;
	}
	
	@Transactional
	public GenericJsonResult<Map<String, Object>> saveInfo(Long userid, String province, String sex, String phone) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		UserInfo userinfo = new UserInfo();
		userinfo.setUserid(userid);
		userinfo.setProvince(province);
		userinfo.setSex(sex);
		userinfo.setPhone(phone);
		userinfoRepository.save(userinfo);
		return result;
	}
	
	@Transactional
	public GenericJsonResult<UserInfo> synchInfo(Long userid, String province, String sex, String phone) {
		GenericJsonResult<UserInfo> result = new GenericJsonResult<UserInfo>(HResult.S_OK);
		UserInfo userinfo = userinfoRepository.findByUserid(userid);
		if (userinfo == null) {
			userinfo = new UserInfo();
			userinfo.setUserid(userid);
		}
		if (!province.equals("province")) {
			userinfo.setProvince(province);
		}
		if (!sex.equals("no_exist")) {
			userinfo.setSex(sex);
		}
		if (!phone.equals("no_exist")) {
			userinfo.setPhone(phone);
		}
		userinfoRepository.save(userinfo);
		result.setData(userinfo);
		return result;
	}
}
