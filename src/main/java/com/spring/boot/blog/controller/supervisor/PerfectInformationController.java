package com.spring.boot.blog.controller.supervisor;


import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/supervisor")
public class PerfectInformationController {
 

	@Autowired
	private TeacherService teacherService;
	
	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping("/perfectInformation")
	public ModelAndView teacherShow(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long id = Long.parseLong(user.getUserName());
		Teacher supervisor = teacherService.getTeacherById(id);
		model.addAttribute("supervisor", supervisor);
		return new ModelAndView("perfectInformation/list", "model", model);
	}
	
	@GetMapping("/teacherInformation")
	public ResponseEntity<Response> TeacherShow() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = Long.valueOf(user.getUsername());
		Teacher teacher ;
		try {
			teacher = teacherService.getTeacherById(id);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "显示成功", teacher));
	}
	
	@GetMapping("/updateTeacherInformation")
	public ResponseEntity<Response> TeacherEdit(String expert,String expectStudent){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = Long.valueOf(user.getUsername());
		Teacher teacher = teacherService.getTeacherById(id);
		teacher.setExpert(expert);
		teacher.setExpectStudent(expectStudent);
		try {
			teacherService.saveTeacher(teacher);
		}catch (RuntimeException e)  {
			return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		}catch (Exception e)  {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "更新成功", teacher));
	}
	
	
}
