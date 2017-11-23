package com.spring.boot.blog.controller.teachers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.CourseStandard;
import com.spring.boot.blog.domain.SubmitFile;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.CourseService;
import com.spring.boot.blog.service.CourseStandardService;
import com.spring.boot.blog.service.SubmitFileService;
import com.spring.boot.blog.util.FileUtil;
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

	@Autowired
	private CourseStandardService courseStandardService;

	@Autowired
	private SubmitFileService submitFileService;

	/**
	 * 查询所有课程
	 * 
	 * @return
	 */
	@GetMapping("/courseList")
	public ModelAndView listCourse(HttpServletRequest request,
			@RequestParam(value = "async", required = false) boolean async,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name, Model model) {

		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");

		User tuser = (User) securityContextImpl.getAuthentication().getPrincipal();

		Long id = Long.parseLong(tuser.getUserName());

		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<Course> page = courseService.listCoursesByTeacherIdAndStatus(id, "1", pageable);
		List<Course> list = page.getContent(); // 当前所在页面数据列表

		model.addAttribute("page", page);
		model.addAttribute("courseList", list);
		return new ModelAndView(async == true ? "teachersCourse/list :: #mainContainerRepleace" : "teachersCourse/list",
				"courseModel", model);
	}

	/**
	 * 课程评分标准页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editStandard/{id}")
	public ModelAndView createFormeditStandard(@PathVariable("id") Long id, Model model) {
		Course course = courseService.getCourseById(id);
		model.addAttribute("course", course);
		List<CourseStandard> standard = courseStandardService.listCourseStandardsByCourseId(id);
		for (int i = standard.size(); i < 7; i++) {
			CourseStandard coursestandard = new CourseStandard();
			coursestandard.setCourse(course);
			coursestandard.setMethod("0");
			coursestandard.setPercentage((long) 0);
			standard.add(coursestandard);
		}
		model.addAttribute("standard", standard);
		return new ModelAndView("teachersCourse/edit", "teacherModel", model);
	}

	/**
	 * 上传文件页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/upload/{id}")
	public ModelAndView createFormUpload(@PathVariable("id") Long id, Model model) {
		Course course = courseService.getCourseById(id);
		model.addAttribute("course", course);

		String outlineName = "0";
		String scheduleName = "0";
		SubmitFile submitFile = submitFileService.getSubmitFileById(id);
		if (submitFile != null) {
			outlineName = submitFile.getOutlineName();
			scheduleName = submitFile.getScheduleName();
		}
		model.addAttribute("outlineName", outlineName);
		model.addAttribute("scheduleName", scheduleName);
		return new ModelAndView("teachersCourse/upload", "courseModel", model);
	}

	/**
	 * 开启课程
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/startCourse/{id}")
	public ResponseEntity<Response> publish(@PathVariable("id") Long id, Model model) {
		try {
			List<CourseStandard> standard = courseStandardService.listCourseStandardsByCourseId(id);
			SubmitFile submitFile = submitFileService.getSubmitFileById(id);
			Course course = courseService.getCourseById(id);
			if (course.getSupervisor().isEmpty()) {
				return ResponseEntity.ok().body(new Response(false, "系主任必须先指派指导老师！"));
			} else if (standard.isEmpty()) {
				return ResponseEntity.ok().body(new Response(false, "必须先设定评分标准！"));
			} else if (submitFile == null) {
				return ResponseEntity.ok().body(new Response(false, "必须先提交大纲与教学进度表！"));
			} else {
				course.setStatus("2");
				courseService.saveCourse(course);
			}
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));
	}

	/**
	 * 保存课程评分标准
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/courseStandard")
	public ResponseEntity<Response> saveCourseStandard(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam(value = "method", required = false, defaultValue = "") String[] method,
			@RequestParam(value = "percentage", required = false, defaultValue = "") Long[] percentage) {
		try {
			Course course = courseService.getCourseById(id);
			List<CourseStandard> standard = courseStandardService.listCourseStandardsByCourseId(id);
			for (CourseStandard courseStandard : standard) {
				courseStandardService.removeCourseStandard(courseStandard.getId());
			}
			for (int i = 0; i < 6; i++) {
				if (percentage[i] != null) {
					CourseStandard courseStandard = new CourseStandard();
					courseStandard.setCourse(course);
					courseStandard.setMethod(method[i]);
					courseStandard.setPercentage(percentage[i]);
					courseStandardService.saveCourseStandard(courseStandard);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));
	}

	/**
	 * 上传大纲
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadOutline")
	public ResponseEntity<Response> saveOutline(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String filePath = "/usr/local/image/";
		SimpleDateFormat tempDate = new SimpleDateFormat("YYYYMMddHHmmss");
		String datetime = tempDate.format(new Date(System.currentTimeMillis()));
		try {
			FileUtil.uploadFile(file.getBytes(), filePath,
					datetime + id + "1" + fileName.substring(fileName.lastIndexOf(".")));
			SubmitFile submitFile;
			submitFile = submitFileService.getSubmitFileById(id);
			if (submitFile == null) {
				submitFile = new SubmitFile();
				submitFile.setId(id);
				submitFile.setScheduleName("0");
			}
			submitFile.setId(id);
			submitFile.setOutlineName(fileName);
			submitFile.setOutlineSaveName(datetime + id + "1" + fileName.substring(fileName.lastIndexOf(".")));
			submitFile.setOutlineUpdateTime(datetime);
			submitFileService.saveSubmitFile(submitFile);
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", fileName));

	}

	/**
	 * 上传教学进度表
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadSchedule")
	public ResponseEntity<Response> saveSchedule(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String filePath = "/usr/local/image/";
		SimpleDateFormat tempDate = new SimpleDateFormat("HHmmss");
		String datetime = tempDate.format(new Date(System.currentTimeMillis()));
		try {
			FileUtil.uploadFile(file.getBytes(), filePath,
					datetime + id + "2" + fileName.substring(fileName.lastIndexOf(".")));
			SubmitFile submitFile;
			submitFile = submitFileService.getSubmitFileById(id);
			if (submitFile == null) {
				submitFile = new SubmitFile();
				submitFile.setId(id);
				submitFile.setOutlineName("0");
			}
			submitFile.setScheduleName(fileName);
			submitFile.setScheduleSaveName(datetime + id + "2" + fileName.substring(fileName.lastIndexOf(".")));
			submitFile.setScheduleUpdateTime(datetime);
			submitFileService.saveSubmitFile(submitFile);
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", fileName));

	}

	/**
	 * 删除大纲
	 * 
	 * @param file
	 * @return
	 */
	@GetMapping("/deleteOutline")
	public ResponseEntity<Response> deleteOutline(
			@RequestParam(value = "id", required = false, defaultValue = "") String id) {
		try {
			Long it = Long.valueOf(id);
			SubmitFile submitFile = submitFileService.getSubmitFileById(it);

			File folder = new File("/usr/local/image/");
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.getName().equals(submitFile.getOutlineSaveName())) {
					file.delete();
				}
			}

			submitFile.setOutlineName("0");
			submitFile.setOutlineSaveName("0");
			submitFile.setOutlineUpdateTime("0");
			submitFileService.saveSubmitFile(submitFile);

		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));

	}

	/**
	 * 删除教学进度表
	 * 
	 * @param file
	 * @return
	 */
	@GetMapping("/deleteSchedule")
	public ResponseEntity<Response> deleteSchedule(
			@RequestParam(value = "id", required = false, defaultValue = "") String id) {
		try {
			Long it = Long.valueOf(id);
			SubmitFile submitFile = submitFileService.getSubmitFileById(it);

			File folder = new File("/usr/local/image/");
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.getName().equals(submitFile.getScheduleSaveName())) {
					file.delete();
				}
			}

			submitFile.setScheduleName("0");
			submitFile.setScheduleSaveName("0");
			submitFile.setScheduleUpdateTime("0");
			submitFileService.saveSubmitFile(submitFile);

		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));

	}

}
