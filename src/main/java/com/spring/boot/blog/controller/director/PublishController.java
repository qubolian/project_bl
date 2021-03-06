package com.spring.boot.blog.controller.director;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.DepartmentList;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.service.CourseService;
import com.spring.boot.blog.service.DepartmentListService;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/director")
public class PublishController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private DepartmentListService departmentListService;

	/**
	 * 查询所有课程
	 * 
	 * @return
	 */
	@GetMapping("/publishCourseList")
	public ModelAndView listCourse(@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name, Model model) {

		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<Course> page = courseService.listCoursesByNameLike(name, "1", pageable);
		List<Course> list = page.getContent(); // 当前所在页面数据列表

		List<Teacher> teachers = teacherService.listTeachers();
		model.addAttribute("teachers", teachers);

		model.addAttribute("page", page);
		model.addAttribute("courseList", list);
		return new ModelAndView(async == true ? "publish/list :: #mainContainerRepleace" : "publish/list",
				"courseModel", model);
	}

	/**
	 * 课程负责人页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/addTeacher/{id}")
	public ModelAndView addTeacher(@PathVariable("id") Long id, Model model) {
		Course course = courseService.getCourseById(id);
		List<Teacher> teacher = teacherService.listTeachers();
		model.addAttribute("teacher", teacher);
		model.addAttribute("course", course);
		return new ModelAndView("publish/addTeacher", "teacherModel", model);
	}

	/**
	 * 指导老师页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping("/addSupervisors/{id}")
	public ModelAndView addSupervisors(@PathVariable("id") Long id, Model model) {

		Course course = courseService.getCourseById(id);
		List<Teacher> supervisiorSelect = course.getSupervisor();
		String deptmentSelect = "0";
		String teacherSelect = "0";
		for (Teacher teacher : supervisiorSelect) {

			deptmentSelect = teacher.getDepartment().getId() + "," + deptmentSelect;
			teacherSelect = teacher.getId() + "," + teacherSelect;

		}
		/*
		 * deptmentSelect = deptmentSelect.substring(0,deptmentSelect.length() -
		 * 1); teacherSelect =
		 * teacherSelect.substring(0,teacherSelect.length()-1);
		 */

		String[] depts = deptmentSelect.split(",");
		Long[] dept = new Long[depts.length - 1];

		for (int i = 0; i < (depts.length - 1); i++) {
			dept[i] = Long.parseLong(depts[i]);

			DepartmentList department = new DepartmentList();
			department.setId(dept[i]);
			List<Teacher> teacherList = teacherService.listTeacherByDepartment(department);

			model.addAttribute("teacherList" + i, teacherList);

		}

		for (int i = (depts.length - 1); i < 5; i++) {
			List<Teacher> teacherList = new ArrayList<Teacher>();
			Teacher teacher = new Teacher();
			teacher.setId((long) 0);
			teacher.setTeacherName("0");
			teacherList.add(teacher);
			model.addAttribute("teacherList" + i, teacherList);
		}

		List<DepartmentList> departmentLists = departmentListService.listDepartmentLists();

		model.addAttribute("departmentLists", departmentLists);
		model.addAttribute("teacher", new Teacher());
		model.addAttribute("course", course);
		model.addAttribute("deptmentSelect", deptmentSelect);
		model.addAttribute("teacherSelect", teacherSelect);

		return new ModelAndView("publish/addsupervisors", "teacherModel", model);
	}

	/**
	 * 根据院系获取教师列表
	 * 
	 * @param departmentId
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping("/addSupervisor/{departmentId}")
	public ResponseEntity<Response> addSupervisor(@PathVariable("departmentId") Long departmentId)
			throws JsonProcessingException {
		List<Teacher> teacher = new ArrayList<Teacher>();
		String jsonlist = new String();
		try {
			DepartmentList department = new DepartmentList();
			department.setId(departmentId);
			teacher = teacherService.listTeacherByDepartment(department);

			ObjectMapper mapper = new ObjectMapper();
			jsonlist = mapper.writeValueAsString(teacher);
		} catch (ConstraintViolationException e) {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "显示成功", jsonlist));
	}

	/**
	 * 增加指导老师数量
	 * 
	 * @return
	 */
	@GetMapping("/addASupervisor")
	public List<DepartmentList> addASupervisor() {
		List<DepartmentList> departmentLists = departmentListService.listDepartmentLists();
		return departmentLists;
	}

	/**
	 * 增加课程负责人
	 * 
	 * @param id
	 * @param tid
	 * @return
	 */
	@GetMapping("/selectTeacher")
	public ResponseEntity<Response> saveOrUpdateTeacher(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam(value = "tid", required = false, defaultValue = "") Long tid) {
		try {
			Teacher teacher = new Teacher();
			teacher.setId(tid);
			Course course = courseService.getCourseById(id);
			course.setTeacher(teacher);
			courseService.saveCourse(course);

		} catch (ConstraintViolationException e) {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));
	}

	/**
	 * 增加指导老师
	 * 
	 * @param id
	 * @param tid
	 * @return
	 */
	@GetMapping("/selectSupervisor")
	public ResponseEntity<Response> saveOrUpdateSupervisor(

			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam(value = "tid", required = false, defaultValue = "") String tid) {
		if ("".equals(tid)) {
			return ResponseEntity.ok().body(new Response(false, "提交失败，指导老师不能为空！"));
		}
		try {
			String[] tids = tid.split(",");

			Long[] tidL = new Long[tids.length];

			for (int i = 0; i < tids.length; i++) {
				tidL[i] = Long.valueOf(tids[i]);
			}
			Set<Long> set = new HashSet<Long>(Arrays.asList(tidL));

			List<Teacher> teacher = new ArrayList<Teacher>();

			for (Long sid : set) {
				Teacher teach = teacherService.getTeacherById(sid);
				teacher.add(teach);
			}

			Course course = courseService.getCourseById(id);
			course.setSupervisor(teacher);
			courseService.saveCourse(course);

		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));
	}
}
