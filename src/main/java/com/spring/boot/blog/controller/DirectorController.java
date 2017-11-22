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
@RequestMapping("/director/menu")
public class DirectorController {

	/**
	 * 获取后台管理主页面
	 * 
	 * @return
	 */
	@GetMapping
	public ModelAndView listUsers(Model model) {
		List<Menu> directorList = new ArrayList<>();

		directorList.add(new Menu("添加课程", "/director/courseList"));
		directorList.add(new Menu("课程管理", "/director/publishCourseList"));
		directorList.add(new Menu("上传文件", "/director/upload"));

		model.addAttribute("directorList", directorList);

		return new ModelAndView("director/menu/index", "model", model);
	}

}
