package com.spring.boot.blog.controller.director;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.service.CourseService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.util.FileUtil;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/director")
public class CourseController {
 

	
	@Autowired
	private CourseService courseService;
	

	
	/**
	 * 查询所有课程
	 * @return
	 */
	@GetMapping("/courseList")
	public ModelAndView listCourse(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<Course> page = courseService.listCoursesByNameLike(name, "0", pageable);
		List<Course> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("courseList", list);
		return new ModelAndView(async==true?"course/list :: #mainContainerRepleace":"course/list", "courseModel", model);
	}
	
	@GetMapping("/addCourse")
	public ModelAndView createForm(Model model) {
		model.addAttribute("course", new Course());
		return new ModelAndView("course/add", "courseModel", model);
	}
	
	
	@PostMapping("/addCourse")
	public ResponseEntity<Response> saveOrUpdate(Course course) {
		try {
			courseService.saveCourse(course);
			
		}catch (RuntimeException e)  {
			return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		}catch (Exception e)  {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", course));
	}
	

	@GetMapping(value = "editCourse/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		Course course= courseService.getCourseById(id);	
		
		model.addAttribute("course", course);
		return new ModelAndView("course/edit", "courseModel", model);
	}
	
	
	/**
	 * 删除课程
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/course/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			if(courseService.getCourseById(id)!=null){
				courseService.removeCourse(id);
			}
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	
	/**
	 * 发布课程
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/publishCourse/{id}")
	public ResponseEntity<Response> publish(@PathVariable("id") Long id, Model model) {
		try {
			Course course= courseService.getCourseById(id);	
			course.setStatus("1");
			courseService.saveCourse(course);
			
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	 
}
