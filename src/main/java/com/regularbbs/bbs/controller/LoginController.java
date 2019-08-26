package com.regularbbs.bbs.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.regularbbs.bbs.dto.User;
import com.regularbbs.bbs.service.UserService;

@Controller
@SessionAttributes(value="userId")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	//로그인
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(@RequestParam("userId") String userId,
			@RequestParam("password") String password, Model model) throws Exception{
		User user = userService.login(userId, password);
		if(user!=null) {
			model.addAttribute("userId", user.getUserId());
			model.addAttribute("msg", "success");
			return "redirect:http://localhost:8090/bbs/main";
		}else {
			model.addAttribute("msg", "fail");
			System.out.println("실패");
			return "redirect:http://localhost:8090/bbs/main";
		}
	}
	
	//로그아웃
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status, Model model, HttpServletResponse response, HttpSession session) throws Exception{
		if(session.getAttribute("userId")!=null) {
			status.setComplete();
		}else {
			model.addAttribute("logoutMsg", "fail");
		}
		return "main";
	}
	
	//회원가입 페이지 열기
	@RequestMapping(value="/member/signUp", method=RequestMethod.GET)
	public String getSignupPage() {
		return "signup";
	}
	
	//아이디 검사
	@ResponseBody
	@RequestMapping(value="/member/idCheck", method=RequestMethod.POST)
	public User idCheck(HttpServletResponse response, 
			@RequestParam(value="userId", required=false) String userId) throws Exception{
		return userService.idCheck(userId);
	}
	
	//이메일 검사
	@ResponseBody
	@RequestMapping(value="/member/emailCheck", method=RequestMethod.POST)
	public int emailCheck(HttpServletResponse response, 
			@RequestParam(value="email", required=false) String email) throws Exception {
		System.out.println(email);
		return userService.emailCheck(email);
	}
	
	//회원가입
	@RequestMapping(value="/member/signupAction", method=RequestMethod.POST)
	public String signUp(HttpSession session, 
			@ModelAttribute User user, BindingResult result, Model model) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("msg", "fail");
			return "signup";
		}
		userService.insertMember(user);
		return "main";
	}
	
	//마이 페이지 열고 데이터 가져오기
	@GetMapping(path="/mypage")
	public String getMyPage(HttpSession session, Model model) throws Exception{
		String userId = session.getAttribute("userId").toString();
		System.out.println(userId);
		User user = userService.getUserInfo(userId);
		model.addAttribute("user", user);
		return "mypage";
	}
}
