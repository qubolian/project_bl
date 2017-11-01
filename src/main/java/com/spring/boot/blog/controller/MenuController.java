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
		superList.add(new Menu("信息类别","/super/newsTypeList"));
		superList.add(new Menu("发布单位","/super/departmentListList"));
		superList.add(new Menu("最新消息","/super/whatsNewList"));
		superList.add(new Menu("组队方法","/super/howtoTeamUpList"));
		superList.add(new Menu("组长&组员责任","/super/leaderMemberResponsibility"));
		superList.add(new Menu("教师管理","/super/teacherList"));
		superList.add(new Menu("学生管理","/super/studentList"));
		model.addAttribute("superList", superList);
		
		return new ModelAndView("menu/index", "model", model);
	}
 
	 
}
