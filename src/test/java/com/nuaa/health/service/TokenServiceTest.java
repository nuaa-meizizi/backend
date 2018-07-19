package com.nuaa.health.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {
	@Autowired
	TokenService tokenservice;
	
	@Test
	public void testAddToken() {
		String token = tokenservice.addToken((long) 2);
		System.out.println(token);
	}

	@Test
	public void testDeleteToken() {
		tokenservice.deleteToken((long) 2);
	}
	
	@Test
	public void testUpdateToken() {
		tokenservice.updateToken((long) 2);
	}

}
