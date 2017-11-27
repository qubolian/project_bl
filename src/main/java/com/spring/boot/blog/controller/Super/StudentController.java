package com.spring.boot.blog.controller.Super;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Authority;
import com.spring.boot.blog.domain.DepartmentList;
import com.spring.boot.blog.domain.Student;
import com.spring.boot.blog.domain.SubmitFile;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.AuthorityService;
import com.spring.boot.blog.service.DepartmentListService;
import com.spring.boot.blog.service.StudentService;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.service.UserService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.util.FileUtil;
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
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private static final String localURL = "D:/image/";
	
	
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
			
			String s = String.valueOf(student.getId());
			User  user = (User)userDetailsService.loadUserByUsername(s);
			if(user==null) {
				//为Student新增一个User账户
				user = new User(student.getName(), (long)0, "student"+student.getId()+"@qq.com",student.getId().toString()); 
			}
			user.setName(student.getName());
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

		}catch (RuntimeException e)  {
			return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		}catch (Exception e)  {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
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
				String s = String.valueOf(id);
				User  user = (User)userDetailsService.loadUserByUsername(s);
				if(user!=null){
					userService.removeUser(user.getId());
				}
			}
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	
	
	/**
	 * 上传学生名单页面
	 * @param model
	 * @return
	 */
	@GetMapping("/uploadStudentList")
	public ModelAndView uploadStudent(Model model) {
		
		return new ModelAndView("student/upload", "studentModel", model);
	}
	
	 /**
	 * 上传学生名单
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadStudent")
	public ResponseEntity<Response> uploadStudent(@RequestParam("file") MultipartFile file) {
	    String fileName = file.getOriginalFilename();
	    String filePath = localURL;
	    
	    try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);

        } catch (Exception e) {
        	return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
        }
	    return ResponseEntity.ok().body(new Response(true, "处理成功"));
	
	}
	
	 
}
