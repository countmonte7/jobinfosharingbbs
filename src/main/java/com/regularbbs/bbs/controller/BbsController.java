package com.regularbbs.bbs.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.regularbbs.bbs.dao.BbsDao;
import com.regularbbs.bbs.dto.Bbs;
import com.regularbbs.bbs.service.BbsService;

@Controller
public class BbsController {
	@Autowired
	BbsService bbsService;
	
	@Autowired
	BbsDao bbsDao;
	
	//게시글 리스트 불러오기
	@GetMapping(path="/list")
	public String list(@RequestParam(name="start", required=false, defaultValue="0") int start,
			ModelMap model) {
		List<Bbs> list = bbsService.getBbs(start);
		int count = bbsService.getCount();
		int pageIndex = count / bbsService.LIMIT;
		if(count % bbsService.LIMIT > 0) {
			pageIndex++;
		}
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i=0;i<pageIndex;i++) {
			int startPageNum = i * bbsService.LIMIT;
			pageStartList.add(startPageNum);
		}
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		return "list";
	}
	//글 보기
	@GetMapping(path="/detailview")
	public String getBbs(@RequestParam(name="id", required=true) int id,
			ModelMap model, HttpServletResponse response, HttpServletRequest request) {
		
		
		Bbs bbs = bbsService.getBbsById(id);
		int viewCnt = bbs.getCount();
		String ip = request.getRemoteAddr();
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		if(cookies!=null && cookies.length > 0) {
			for(int i=0; i<cookies.length;i++) {
				if(cookies[i].getName().equals("cookie" + id)) {
					viewCookie = cookies[i];
				}
			}
		}
		if(viewCookie == null) {
			Cookie cookie = new Cookie("cookie" + id, "|" + id + "|");
			cookie.setMaxAge(60*60*1);
			response.addCookie(cookie);
			++viewCnt;
			bbs.setCount(viewCnt);
			bbsService.updateBbs(bbs, ip);
		}
		model.addAttribute("bbs", bbs);
		return "detailview";
	}
	
	//글쓰기 페이지 가져오기
	@GetMapping(path="/writeBbs")
	public String writeBbs() {
		return "writeBbs";
	}
	//메인 페이지 가져오기
	@GetMapping(path="/main")
	public String getMain() {
		return "main";
	}
	
	//회원가입 페이지 가져오기
	@GetMapping(path="/signUp")
	public String getSignUp() {
		return "signup";
	}
	//게시글 삭제
	@GetMapping(path="/deleteBbs")
	public String deleteBbs(@RequestParam(name="id", required=true) int id,
			ModelMap model) {
		int result = bbsService.deleteBbs(id);
		String message = "";
		if(result > 0) {
			message = "success";
			model.addAttribute("message", message);
		}else {
			message = "fail";
			model.addAttribute("message", message);
		}
		return "deleteBbs";
	}
	//게시글 등록
	@PostMapping(path="/writeAction")
	public String writeAction(@SessionAttribute("userId") String userId, @ModelAttribute Bbs bbs,
			@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		String fileName = null;
		String folderPath = "C:/devr/";
		try (
				FileOutputStream fos = new FileOutputStream(folderPath + file.getOriginalFilename());
				InputStream is = file.getInputStream();
		) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while((readCount=is.read(buffer))!= -1) {
				fos.write(buffer, 0, readCount);
			}
		}catch(Exception e) {
			throw new RuntimeException("file save error");
		}
		
		if(file!= null) {
			fileName = file.getOriginalFilename();
		}
		String clientIp = request.getRemoteAddr();
		bbs.setUserId(userId);
		bbs.setCount(0);
		bbs.setThumbImg(fileName);
		bbsService.writeBbs(bbs, clientIp);
		return "detailview";
	}
	//게시글 수정 페이지 가져오기
	@GetMapping(path="/updateBbs")
	public String getUpdatePage(@RequestParam(name="id", required=true) int id,
			ModelMap model) {
		Bbs bbs = bbsService.getBbsById(id);
		model.addAttribute("bbs", bbs);
		return "updateBbs";
	}
	//게시글 수정
	@RequestMapping(value="/updateBbs", method=RequestMethod.POST)
	public String updateBbs(Bbs bbs, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		bbsService.updateBbs(bbs, ip);
		return "detailview";
	}
}
