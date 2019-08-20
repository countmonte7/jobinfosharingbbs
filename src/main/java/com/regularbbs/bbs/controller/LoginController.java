package com.regularbbs.bbs.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.regularbbs.bbs.dto.User;
import com.regularbbs.bbs.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(@RequestParam("userId") String userId,
			@RequestParam("password") String password, HttpSession session, Model model) throws Exception{
		User user = userService.login(userId, password);
		if(user!=null) {
			System.out.println("예스");
			session.setAttribute("user", user);
			model.addAttribute("msg", "success");
			return "redirect:http://localhost:8090/bbs/main";
		}else {
			model.addAttribute("msg", "fail");
			System.out.println("실패");
			return "redirect:http://localhost:8090/bbs/main";
		}
	}
	
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, Model model, HttpServletResponse response) throws Exception{
		if(session.getAttribute("user")!=null) {
			session.invalidate();
		}else {
			model.addAttribute("logoutMsg", "fail");
		}
		return "main";
	}
}
