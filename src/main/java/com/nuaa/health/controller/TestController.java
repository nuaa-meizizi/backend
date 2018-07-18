package com.nuaa.health.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
	@RequestMapping(value = "/hello",method=RequestMethod.GET)
    public String postLogin() {

		return "hello";
    }
	
}