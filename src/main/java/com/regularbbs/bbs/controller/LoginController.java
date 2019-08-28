package com.regularbbs.bbs.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	
	@Autowired
	private JavaMailSender mailSender;
	
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
	@RequestMapping(value="/member/idCheck", method=RequestMethod.GET)
	public User idCheck(HttpServletResponse response, 
			@RequestParam(value="userId", required=false) String userId) throws Exception{
		User user = userService.idCheck(userId);
		return user;
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
		User newUser = userService.insertMember(user);
		if(newUser!=null) {
			mailSending(newUser.getEmail());
		}
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
	
	public void mailSending(String email) {
		String frommail = "countmonte7@naver.com";
		String tomail = email;
		String title = "회원가입 확인 메일";
		String content = "회원가입이 되셨습니다. 축하합니다.";
		try {
			MimeMessage message= mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(frommail);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(content);
			mailSender.send(message);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
