package com.spring.boot.blog.controller.Super;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.spring.boot.blog.domain.Student;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.AuthorityService;
import com.spring.boot.blog.service.StudentService;
import com.spring.boot.blog.service.UserService;
import com.spring.boot.blog.util.ImportExcelUtil;
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
	private AuthorityService authorityService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private static final String localURL = "D:/image/";

	/**
	 * 查询所有学生
	 * 
	 * @return
	 */
	@GetMapping("/studentList")
	public ModelAndView listStudent(@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name, Model model) {

		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<Student> page = studentService.listStudentByNameLike(name, pageable);
		List<Student> list = page.getContent(); // 当前所在页面数据列表

		model.addAttribute("page", page);
		model.addAttribute("studentList", list);
		return new ModelAndView(async == true ? "student/list :: #mainContainerRepleace" : "student/list",
				"studentModel", model);
	}

	@GetMapping("/addStudent")
	public ModelAndView createForm(Model model) {
		model.addAttribute("student", new Student());

		return new ModelAndView("student/add", "studentModel", model);
	}

	@PostMapping("/addStudent")
	public ResponseEntity<Response> saveOrUpdate(Student student) {
		try {
			if(student.getStatus().equals("注册学籍")){
				student.setStatusInt(0);
			}else if(student.getStatus().equals("休学")) {
				student.setStatusInt(1);
			}else if(student.getStatus().equals("退学")) {
				student.setStatusInt(2);
			}else if(student.getStatus().equals("保留学籍")) {
				student.setStatusInt(3);
			}
			studentService.saveStudent(student);

			String s = String.valueOf(student.getId());
			User user = (User) userDetailsService.loadUserByUsername(s);
			if (user == null) {
				// 为Student新增一个User账户
				user = new User(student.getName(), (long) 0, "student" + student.getId() + "@qq.com",
						student.getId().toString());
			}
			user.setName(student.getName());
			user.setPassword("123456");
			List<Authority> authorities = new ArrayList<>();
			authorities.add(authorityService.getAuthorityById((long) 4));
			user.setAuthorities(authorities);
			if (user.getId() == 0) {
				user.setEncodePassword(user.getPassword()); // 加密密码
			} else {
				// 判断密码是否做了变更
				User originalUser = userService.getUserById(user.getId());
				String rawPassword = originalUser.getPassword();
				PasswordEncoder encoder = new BCryptPasswordEncoder();
				String encodePasswd = encoder.encode(user.getPassword());
				boolean isMatch = encoder.matches(rawPassword, encodePasswd);
				if (!isMatch) {
					user.setEncodePassword(user.getPassword());
				} else {
					user.setPassword(user.getPassword());
				}
			}
			userService.saveUser(user);

		} catch (RuntimeException e) {
			return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", student));
	}

	@GetMapping(value = "editStudent/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);

		return new ModelAndView("student/edit", "studentModel", model);
	}

	/**
	 * 删除学生
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/student/{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			if (studentService.getStudentById(id) != null) {
				Student student = studentService.getStudentById(id);
				student.setStatusInt(2);
				student.setStatus("退学");
				studentService.saveStudent(student);
				//studentService.removeStudent(id);
				String s = String.valueOf(id);
				User user = (User) userDetailsService.loadUserByUsername(s);
				if (user != null) {
					List<Authority> authorities = new ArrayList<>();
					user.setAuthorities(authorities);
					userService.saveUser(user);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));
	}
	
	/**
	 * 恢复学生
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/undoStudent/{id}")
	public ResponseEntity<Response> undoStudent(@PathVariable("id") Long id, Model model) {
		try {
			if (studentService.getStudentById(id) != null) {
				
				
				Student student = studentService.getStudentById(id);
				student.setStatusInt(0);
				student.setStatus("注册学籍");
				studentService.saveStudent(student);
				//studentService.removeStudent(id);
				String s = String.valueOf(id);
				User user = (User) userDetailsService.loadUserByUsername(s);
				List<Authority> authorities = new ArrayList<>();
				authorities.add(authorityService.getAuthorityById((long) 4));
				user.setAuthorities(authorities);
				userService.saveUser(user);
			}
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));
	}

	/**
	 * 上传学生名单页面
	 * 
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
	 * @throws Exception
	 */
	@PostMapping("/uploadStudent")
	public ResponseEntity<Response> uploadStudent(@RequestParam("file") MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();

		Sheet sheet = null;
		Cell cell = null;
		Row row = null;
		InputStream inputStream = file.getInputStream();
		ImportExcelUtil importExcelUtil = new ImportExcelUtil();
		Workbook workbook = importExcelUtil.getWorkbook(inputStream, fileName);
		sheet = workbook.getSheetAt(0);

		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) {

			row = sheet.getRow(j);

			if (row == null || row.getFirstCellNum() == j) {
				continue;
			}

			Student student = new Student();

			cell = row.getCell(0);
			Long uid = Long.parseLong(cell.getStringCellValue());
			student.setId(uid);

			cell = row.getCell(1);
			String username = cell.getStringCellValue();
			student.setName(username);

			cell = row.getCell(2);
			String sex = cell.getStringCellValue();
			student.setSex(sex);

			cell = row.getCell(3);
			String grade = cell.getStringCellValue();
			student.setGrade(grade);

			cell = row.getCell(4);
			String lengthOfSchooling = cell.getNumericCellValue() + "";
			student.setLengthOfSchooling(lengthOfSchooling);

			cell = row.getCell(5);
			String trainingLevel = cell.getStringCellValue();
			student.setTrainingLevel(trainingLevel);

			cell = row.getCell(6);
			String department = cell.getStringCellValue();
			student.setDepartment(department);

			cell = row.getCell(7);
			String major = cell.getStringCellValue();
			student.setMajor(major);

			cell = row.getCell(8);
			// String entranceTime = cell.getNumericCellValue() + "";
			String entranceTime = sdf.format(cell.getDateCellValue());

			student.setEntranceTime(entranceTime);

			cell = row.getCell(9);
			String status = cell.getStringCellValue();
			student.setStatus(status);

			cell = row.getCell(10);
			String classes = cell.getStringCellValue();
			student.setClasses(classes);

			studentService.saveStudent(student);

			User user = new User(student.getName(), (long) 0, "student" + student.getId() + "@qq.com",
					student.getId().toString());

			user.setName(student.getName());
			user.setPassword("123456");
			List<Authority> authorities = new ArrayList<>();
			authorities.add(authorityService.getAuthorityById((long) 4));
			user.setAuthorities(authorities);
			user.setEncodePassword(user.getPassword());
			userService.saveUser(user);

		}

		return ResponseEntity.ok().body(new Response(true, "处理成功"));

	}

}
