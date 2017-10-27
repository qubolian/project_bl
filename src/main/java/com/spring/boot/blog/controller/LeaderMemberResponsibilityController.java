package com.spring.boot.blog.controller;


import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.LeaderMemberResponsibility;
import com.spring.boot.blog.service.LeaderMemberResponsibilityService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/super")
public class LeaderMemberResponsibilityController {
 

	@Autowired
	private LeaderMemberResponsibilityService leaderMemberResponsibilityService;
	
	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping("/leaderMemberResponsibility")
	public ModelAndView leaderMemberResponsibilityShow(Model model) {
		LeaderMemberResponsibility lmr = leaderMemberResponsibilityService.getLeaderMemberResponsibilityById(1L);
		model.addAttribute("lmr", lmr);
		return new ModelAndView("leaderMemberResponsibility/list", "model", model);
	}
	
	
	@GetMapping("/leaderMemberResponsibilityList")
	public ResponseEntity<Response> LeaderResponsibilityShow() {
		LeaderMemberResponsibility lmr ;
		try {
			lmr = leaderMemberResponsibilityService.getLeaderMemberResponsibilityById(1L);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "显示成功", lmr));
	}
	
	@GetMapping("/leaderMemberResponsibilityEdit")
	public ResponseEntity<Response> LeaderResponsibilityEdit(String leader,String member){
		
		LeaderMemberResponsibility lmr = new  LeaderMemberResponsibility();
		lmr.setId(1L);
		lmr.setLeaderResponsibility(leader);
		lmr.setMemberResponsibility(member);
		try {
			lmr = leaderMemberResponsibilityService.saveLeaderMemberResponsibility(lmr);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "更新成功", lmr));
	}
	/*
	@GetMapping("/MemberResponsibilityList")
	public ResponseEntity<Response> MemberResponsibilityShow() {
		LeaderMemberResponsibility lmr ;
		try {
			lmr = leaderMemberResponsibilityService.getLeaderMemberResponsibilityById(1L);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "显示成功", lmr));
	}
	
	@GetMapping("/MemberResponsibilityEdit")
	public ResponseEntity<Response> MemberResponsibilityEdit(String member) {
		
		
		LeaderMemberResponsibility lmr = new  LeaderMemberResponsibility();
		lmr.setId(1L);
		lmr.setMisson("123");
		lmr.setMark(mark);
		try {
			lmr = leaderMemberResponsibilityService.saveLeaderMemberResponsibility(lmr);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "更新成功", lmr));
	}*/
	
	 
}
