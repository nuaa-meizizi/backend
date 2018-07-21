package com.nuaa.health.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.health.entity.Token;
import com.nuaa.health.repository.TokenRepository;

@Service
public class TokenService {
	@Autowired
	TokenRepository tokenRepository;
	Long expireTime = (long) (7*24*60*60*1000); 	//7d
	Long addExpireTime = (long) (2*24*60*60*1000); 	//2d
	
	@Transactional
	public String addToken(Long uid) {
		String text = Long.toString(uid) + System.currentTimeMillis() + "scy";
		Token token = new Token();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] results = md5.digest(text.getBytes());
			token.setToken(byteArrayToHexString(results));
			token.setUserId(uid);
			token.setExpire(Long.toString(System.currentTimeMillis()+expireTime));
			tokenRepository.save(token);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token.getToken();
	}

	@Transactional
	public void deleteToken(Long uid) {
		tokenRepository.deleteByUserId(uid);
	}
	
	@Transactional
	public String updateToken(Long uid) {
		Boolean exist = tokenRepository.existsByUserId(uid);
		if (exist) {
			Token token =  tokenRepository.findTokenByUserId(uid);
			if (Long.valueOf(token.getExpire()) > System.currentTimeMillis()) {		//没有过期就延长
				return token.getToken();
			}
			else {		//过期删除新建
				tokenRepository.deleteByUserId(uid);
				return addToken(uid);
			}
		}
		//不存在直接新建一个
		return addToken(uid);
	}
	
	@Transactional
	public Long getUid(String token) {
		Token tokenInfo = tokenRepository.findTokenByToken(token);
		if (tokenInfo == null) {
			return null;
		}
		Long uid = tokenInfo.getUserId();
		Long expire = Long.valueOf(tokenInfo.getExpire());
		if (expire < System.currentTimeMillis()) {				//token过期
			tokenRepository.deleteByUserId(uid);  				//顺便删除
			return null;
		}
		return uid;
	}

	//轮换字节数组为十六进制字符串
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	// 将一个字节转化成十六进制形式的字符串
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"}; 
}
