package com.spring.boot.blog.controller.Super;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.TransactionSystemException;
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
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.AuthorityService;
import com.spring.boot.blog.service.DepartmentListService;
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
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private DepartmentListService departmentListService;
	
	@Autowired
	private AuthorityService  authorityService;
	
	@Autowired
	private UserService userService;
	/**
	 * 查询所有教师
	 * @return
	 */
	@GetMapping("/teacherList")
	public ModelAndView listTeacher(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="teacherName",required=false,defaultValue="") String teacherName,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<Teacher> page = teacherService.listTeacherByTeacherNameLike(teacherName, pageable);
		List<Teacher> list = page.getContent();	// 当前所在页面数据列表
		
		List<DepartmentList> departmentLists = departmentListService.listDepartmentLists();
		model.addAttribute("departmentLists", departmentLists);
		
		model.addAttribute("page", page);
		model.addAttribute("teacherList", list);
		return new ModelAndView(async==true?"teacher/list :: #mainContainerRepleace":"teacher/list", "teacherModel", model);
	}
	
	@GetMapping("/addTeacher")
	public ModelAndView createForm(Model model) {
		model.addAttribute("teacher", new Teacher());

		List<DepartmentList> departmentLists = departmentListService.listDepartmentLists();
		model.addAttribute("departmentLists", departmentLists);
		
		return new ModelAndView("teacher/add", "teacherModel", model);
	}
	
	
	@PostMapping("/addTeacher")
	public ResponseEntity<Response> saveOrUpdate(Teacher teacher) {
		try {
			teacherService.saveTeacher(teacher);
			
			//为Teacher新增一个User账户
			User user = new User(teacher.getTeacherName(), (long)0, "teacher"+teacher.getId()+"@qq.com",teacher.getId().toString()); 
			user.setPassword("123456");
			List<Authority> authorities = new ArrayList<>();
			authorities.add(authorityService.getAuthorityById((long) 3));
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
		}catch (RuntimeException e)  {
			return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		}catch (Exception e)  {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", teacher));
	}
	

	@GetMapping(value = "editTeacher/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		Teacher teacher= teacherService.getTeacherById(id);	
		model.addAttribute("teacher", teacher);
		
		List<DepartmentList> departmentLists = departmentListService.listDepartmentLists();
		model.addAttribute("departmentLists", departmentLists);
		
		return new ModelAndView("teacher/edit", "teacherModel", model);
	}
	
	
	/**
	 * 删除教师
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/teacher/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			if(teacherService.getTeacherById(id)!=null){
				teacherService.removeTeacher(id);
			}
		}catch (RuntimeException e) {
			Throwable cause = e.getCause();
		    if(cause instanceof org.hibernate.exception.ConstraintViolationException) {
		    	return  ResponseEntity.ok().body( new Response(false, "请先删除该教师负责的课程"));
		    }
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	
	 
}
