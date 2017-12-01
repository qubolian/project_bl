package com.spring.boot.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Authority;
import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.DepartmentList;
import com.spring.boot.blog.domain.LeaderMemberResponsibility;
import com.spring.boot.blog.domain.ProjectMission;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.domain.WhatsNew;
import com.spring.boot.blog.service.AuthorityService;
import com.spring.boot.blog.service.CourseService;
import com.spring.boot.blog.service.LeaderMemberResponsibilityService;
import com.spring.boot.blog.service.ProjectMissionService;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.service.UserService;
import com.spring.boot.blog.service.WhatsNewService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

@Controller
public class MainController {
private static final Long ROLE_USER_AUTHORITY_ID = 2L;
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService  authorityService;
	
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
	
	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}
	
/*	@GetMapping("/index")
	public String index() {
		
		return "index";
	}*/
	
	@GetMapping("/index")
	public ModelAndView index(Model model) {
		
		//宗旨
		ProjectMission pm = projectMissionService.getProjectMissionById(1L);
		model.addAttribute("pm", pm);
		
		//分页
		int pageIndex = 0;
		int pageSize =5;
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		
		//最新消息
		Page<WhatsNew> page = whatsNewService.listWhatsNewsByEventsLike("", pageable);
		List<WhatsNew> list = page.getContent();	// 当前所在页面数据列表
		model.addAttribute("page", page);
		model.addAttribute("whatsNewList", list);
		
		//课程
		Page<Course> Cpage = courseService.listCoursesByNameLike("", "2", pageable);
		List<Course> courseList = Cpage.getContent();	// 当前所在页面数据列表
		model.addAttribute("courseList", courseList);
		
		//教师
		Page<Teacher> Tpage = teacherService.listTeacherByTeacherNameLike("", pageable);
		List<Teacher> teacherList = Tpage.getContent();	// 当前所在页面数据列表
		List<User> teacher = new ArrayList<User>();
		for (Teacher t : teacherList) {
			String s = String.valueOf(t.getId());
			User  user = (User)userDetailsService.loadUserByUsername(s);
			if(user.getAvatar()!=null) {
				user.setAvatar("http://127.0.0.1/"+user.getAvatar());
			}else {
				user.setAvatar("../images/avatar-defualt.jpg");
			}
			teacher.add(user);
		}
		model.addAttribute("teacher", teacher);
		
		//组长组员责任
		LeaderMemberResponsibility lmr = leaderMemberResponsibilityService.getLeaderMemberResponsibilityById(1L);
		model.addAttribute("lmr", lmr);
		
		//最新小队...
		
		return new ModelAndView("index", "Model", model);
	}

	/**
	 * 获取登录界面
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping("/register")
	public String registerUser(User user) {
		List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
		user.setAuthorities(authorities);
		user.setId(0);
		try {
			userService.saveUser(user);
		}  catch (ConstraintViolationException e)  {
			 ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return "redirect:/login";
	}
	
	@GetMapping("/search")
	public String search() {
		return "search";
	}
	

}
