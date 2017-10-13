package com.spring.boot.blog.controller;


import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/menu")
public class MenuController {
 

	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping
	public ModelAndView listUsers(Model model) {
		List<Menu> superList = new ArrayList<>();
		superList.add(new Menu("编辑宗旨","/super/misson"));
		
		
		
		model.addAttribute("superList", superList);
		
		return new ModelAndView("menu/index", "model", model);
	}
 
	 
}
