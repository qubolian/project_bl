package com.spring.boot.blog.controller;


import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.LeaderMemberResponsibility;
import com.spring.boot.blog.domain.NewsType;
import com.spring.boot.blog.domain.ProjectMission;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.domain.WhatsNew;
import com.spring.boot.blog.service.CourseService;
import com.spring.boot.blog.service.LeaderMemberResponsibilityService;
import com.spring.boot.blog.service.NewsTypeService;
import com.spring.boot.blog.service.ProjectMissionService;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.service.WhatsNewService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.util.HomePage;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/details")
public class DetailsController {
 

	@Autowired
	private ProjectMissionService projectMissionService;
	
	@Autowired
	private WhatsNewService whatsNewService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private LeaderMemberResponsibilityService leaderMemberResponsibilityService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * 获取最新消息列表
	 * @return
	 */
	@GetMapping("/whatsNewList")
	public ModelAndView missionShow(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,Model model) {

		
		HomePage HomePage = new HomePage();
		
		//课程
		List<Course> courseList = HomePage.ListCourse(courseService);
		model.addAttribute("courseList", courseList);
		
		//教师
		List<User> teacher = HomePage.ListTeacher(teacherService,userDetailsService);
		model.addAttribute("teacher", teacher);
		
		//组长组员责任
		LeaderMemberResponsibility lmr = HomePage.ListLeaderMemberResponsibility(leaderMemberResponsibilityService);
		model.addAttribute("lmr", lmr);
		
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<WhatsNew> page = whatsNewService.listWhatsNewsByEventsLike("", pageable);
		List<WhatsNew> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("whatsNewList", list);
		return new ModelAndView("details/whatsNew/list", "Model", model);
	}
	
	/**
	 * 获取最新消息详细记录
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/whatsNew/{id}")
	public ModelAndView whatsNew(@PathVariable("id") Long id,Model model) {
		
		HomePage HomePage = new HomePage();
		
		//课程
		List<Course> courseList = HomePage.ListCourse(courseService);
		model.addAttribute("courseList", courseList);
		
		//教师
		List<User> teacher = HomePage.ListTeacher(teacherService,userDetailsService);
		model.addAttribute("teacher", teacher);
		
		//组长组员责任
		LeaderMemberResponsibility lmr = HomePage.ListLeaderMemberResponsibility(leaderMemberResponsibilityService);
		model.addAttribute("lmr", lmr);
		
		//最新消息
		WhatsNew whatsNew = whatsNewService.getWhatsNewById(id);
		model.addAttribute("whatsNew", whatsNew);
		
		return new ModelAndView("details/whatsNew/detail", "Model", model);
	}
	
	/**
	 * 获取教师详细记录
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/teaherDetail")
	public ModelAndView teaherDetail(
			@RequestParam(value="id",required=false,defaultValue="") String id,
			Model model) {
	
		
		
		return null;
	}
	
	
	
	
}
