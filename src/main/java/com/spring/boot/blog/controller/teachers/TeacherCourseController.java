package com.spring.boot.blog.controller.teachers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.DepartmentList;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.CourseService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/teachers")
public class TeacherCourseController {
 

	
	@Autowired
	private CourseService courseService;
	

	
	/**
	 * 查询所有课程
	 * @return
	 */
	@GetMapping("/courseList")
	public ModelAndView listCourse(HttpServletRequest request,
			@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		
		User tuser =(User) securityContextImpl.getAuthentication().getPrincipal();
		
		Long id = Long.parseLong(tuser.getUserName());
		
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<Course> page = courseService.listCoursesByTeacherIdAndStatus(id, "1", pageable);
		List<Course> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("courseList", list);
		return new ModelAndView(async==true?"teachersCourse/list :: #mainContainerRepleace":"teachersCourse/list", "courseModel", model);
	}
	
	
	@GetMapping("/editStandard/{id}")
	public ModelAndView createFormeditStandard(@PathVariable("id") Long id, Model model) {

		return new ModelAndView("teachersCourse/edit", "teacherModel", model);
	}
	
	@GetMapping("/upload/{id}")
	public ModelAndView createFormUpload(@PathVariable("id") Long id, Model model) {
		
		return new ModelAndView("teachersCourse/upload", "teacherModel", model);
	}
	
	/**
	 * 开启课程
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/startCourse/{id}")
	public ResponseEntity<Response> publish(@PathVariable("id") Long id, Model model) {
		try {
			Course course= courseService.getCourseById(id);	
			course.setStatus("2");
			courseService.saveCourse(course);
			
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	 
}