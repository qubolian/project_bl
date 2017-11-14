package com.spring.boot.blog.controller;


import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.NewsType;
import com.spring.boot.blog.domain.ProjectMission;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.NewsTypeService;
import com.spring.boot.blog.service.ProjectMissionService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/super")
public class SuperController {
 

	@Autowired
	private ProjectMissionService projectMissionService;
	
	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping("/misson")
	public ModelAndView missionShow(Model model) {
		ProjectMission pm = projectMissionService.getProjectMissionById(1L);
		model.addAttribute("pm", pm);
		return new ModelAndView("menu/list", "model", model);
	}
	
	
	@GetMapping("/missionList")
	public ResponseEntity<Response> missionShow() {
		ProjectMission pm ;
		try {
			pm = projectMissionService.getProjectMissionById(1L);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "显示成功", pm));
	}
	
	@GetMapping("/missionEdit")
	public ResponseEntity<Response> missionEdit(String mark) {
		
		
		ProjectMission pm = new  ProjectMission();
		pm.setId(1L);
		pm.setMisson("123");
		pm.setMark(mark);
		try {
			pm = projectMissionService.saveProjectMission(pm);
		}catch (RuntimeException e)  {
			Throwable cause = e.getCause();
		    if(cause instanceof javax.persistence.RollbackException) {
		    	return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		    }
		}catch (Exception e)  {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "更新成功", pm));
	}
	
	 
}
