package com.nuaa.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
	@RequestMapping(value = "/hello",method=RequestMethod.GET)
    public String postLogin() {

		return "hello";
    }
	
}