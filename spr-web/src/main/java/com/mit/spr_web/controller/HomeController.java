package com.mit.spr_web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	@Value("${example.title}")
	private String message;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String renderHome() {
		return message;
	}

}
