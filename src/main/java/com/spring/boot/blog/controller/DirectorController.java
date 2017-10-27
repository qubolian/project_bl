package com.spring.boot.blog.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.ProjectMission;
import com.spring.boot.blog.service.NewsTypeService;
import com.spring.boot.blog.service.ProjectMissionService;
import com.spring.boot.blog.util.Menu;

/**
 * 后台页面控制器.
 * 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月26日
 */
@Controller
@RequestMapping("/director")
public class DirectorController {
 

	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping("/menu")
	public ModelAndView listUsers(Model model) {
		List<Menu> superList = new ArrayList<>();
		
		superList.add(new Menu("编辑宗旨","/director/misson"));
		model.addAttribute("superList", superList);
		
		return new ModelAndView("director/menu/index", "model", model);
	}
	
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
		return new ModelAndView("director/menu/list", "model", model);
	}
	 
}
