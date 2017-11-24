package com.spring.boot.blog.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.spring.boot.blog.util.Menu;

/**
 * 后台页面控制器.
 * 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月26日
 */
@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
 

	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping
	public ModelAndView listTeachers(Model model) {
		List<Menu> supervisorList = new ArrayList<>();
		
		supervisorList.add(new Menu("完善信息","/supervisor/perfectInformation"));
		
		model.addAttribute("supervisorList", supervisorList);
		
		return new ModelAndView("supervisor/index", "model", model);
	}

	

	 
}
