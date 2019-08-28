package com.regularbbs.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecondHandBbsController {

	@GetMapping("/secondhandlist")
	public String getList() {
		return "secondhandlist";
	}
	
}
