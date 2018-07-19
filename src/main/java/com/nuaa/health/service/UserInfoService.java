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

	public GenericJsonResult<Map<String, Object>> getinfo(Long userid) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Map<String, Object> data = new HashMap<String, Object>();  
		Boolean exist = userinfoRepository.existsByUserid(userid);
		if(exist) {
            UserInfo userinfo = new UserInfo();
            userinfo = userinfoRepository.findUserInfoByUserid(userid);
			data.put("userid",userinfo.getUserid());
			data.put("province",userinfo.getProvince());
			data.put("birthday",userinfo.getBirthday());
            result.setData(data);
		}
		else {
            result.setStatus(HResult.E_USER_NOTEXIST);
		}
		return result;
	}
	

	@Transactional
	public GenericJsonResult<Map<String, Object>> saveinfo(Long userid,String province,Long birthday) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Boolean exist = userinfoRepository.existsByUserid(userid);
		if(exist) {
			result.setStatus(HResult.E_USER_EXISTENCE);
		}
		else {
			UserInfo userinfo = new UserInfo();
			userinfo.setUserid(userid);
			userinfo.setProvine(province);
			userinfo.setBirthday(birthday);
			userinfoRepository.save(userinfo);
		}
		return result;
    }

    @Transactional
	public GenericJsonResult<Map<String, Object>> updateinfo(Long userid,String province,Long birthday) {
		GenericJsonResult<Map<String, Object>> result = new GenericJsonResult<Map<String, Object>>(HResult.S_OK);
		Boolean exist = userinfoRepository.existsByUserid(userid);
		if(exist) {
            UserInfo userinfo = new UserInfo();
			userinfo.setUserid(userid);
			userinfo.setProvine(province);
			userinfo.setBirthday(birthday);
			userinfoRepository.save(userinfo);
		}
		else {
            result.setStatus(HResult.E_USER_NOTEXIST);
		}
		return result;
	}
}
