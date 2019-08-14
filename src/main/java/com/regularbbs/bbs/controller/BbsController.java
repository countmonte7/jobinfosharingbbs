package com.regularbbs.bbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.regularbbs.bbs.dao.BbsDao;
import com.regularbbs.bbs.dto.Bbs;
import com.regularbbs.bbs.service.BbsService;

@Controller
public class BbsController {
	@Autowired
	BbsService bbsService;
	
	@Autowired
	BbsDao bbsDao;
	
	
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
	
	@GetMapping(path="/detailview")
	public String getBbs(@RequestParam(name="id", required=true) int id,
			ModelMap model) {
		Bbs bbs = bbsService.getBbsById(id);
		model.addAttribute("bbs", bbs);
		return "detailview";
	}
	
	@GetMapping(path="/writeBbs")
	public String writeBbs() {
		return "writeBbs";
	}
	
	@GetMapping(path="/main")
	public String getMain() {
		return "main";
	}
	
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
	
	@PostMapping(path="/writeAction")
	public String writeAction(@ModelAttribute Bbs bbs,
			HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		bbsService.writeBbs(bbs, clientIp);
		return "redirect:list";
	}
	
	@GetMapping(path="/updateBbs")
	public String getUpdatePage(@RequestParam(name="id", required=true) int id,
			ModelMap model) {
		Bbs bbs = bbsService.getBbsById(id);
		model.addAttribute("bbs", bbs);
		return "updateBbs";
	}
	
	@RequestMapping(value="/updateBbs", method=RequestMethod.POST)
	public String updateBbs(Bbs bbs, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		bbsService.updateBbs(bbs, ip);
		return "detailview";
	}
}
