package com.spring.boot.blog.controller.Super;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Authority;
import com.spring.boot.blog.domain.DepartmentList;
import com.spring.boot.blog.domain.Student;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.AuthorityService;
import com.spring.boot.blog.service.DepartmentListService;
import com.spring.boot.blog.service.StudentService;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.service.UserService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/super")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AuthorityService  authorityService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 查询所有学生
	 * @return
	 */
	@GetMapping("/studentList")
	public ModelAndView listStudent(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<Student> page = studentService.listStudentByNameLike(name, pageable);
		List<Student> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("studentList", list);
		return new ModelAndView(async==true?"student/list :: #mainContainerRepleace":"student/list", "studentModel", model);
	}
	
	@GetMapping("/addStudent")
	public ModelAndView createForm(Model model) {
		model.addAttribute("student", new Student());


		
		return new ModelAndView("student/add", "studentModel", model);
	}
	
	
	@PostMapping("/addStudent")
	public ResponseEntity<Response> saveOrUpdate(Student student) {
		try {
			
			studentService.saveStudent(student);
			
			//为Student新增一个User账户
			User user = new User(student.getName(), (long)0, "student@qq.com",student.getId().toString()); 
			user.setPassword("123456");
			List<Authority> authorities = new ArrayList<>();
			authorities.add(authorityService.getAuthorityById((long) 4));
			user.setAuthorities(authorities);
			if(user.getId() == 0) {
				user.setEncodePassword(user.getPassword()); // 加密密码
			}else {
				// 判断密码是否做了变更
				User originalUser = userService.getUserById(user.getId());
				String rawPassword = originalUser.getPassword();
				PasswordEncoder  encoder = new BCryptPasswordEncoder();
				String encodePasswd = encoder.encode(user.getPassword());
				boolean isMatch = encoder.matches(rawPassword, encodePasswd);
				if (!isMatch) {
					user.setEncodePassword(user.getPassword());
				}else {
					user.setPassword(user.getPassword());
				}
			}
			userService.saveUser(user);

		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", student));
	}
	

	@GetMapping(value = "editStudent/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		Student student= studentService.getStudentById(id);	
		model.addAttribute("student", student);
		

		
		return new ModelAndView("student/edit", "studentModel", model);
	}
	
	
	/**
	 * 删除学生
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/student/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			if(studentService.getStudentById(id)!=null){
				studentService.removeStudent(id);
			}
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	
	 
}
