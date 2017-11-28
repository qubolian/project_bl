package com.spring.boot.blog.controller.supervisor;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.SubmitFile;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.service.TeacherService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.util.FileUtil;
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
	
	private static final String localURL = "D:/image/";
	
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
	
	/**
	 * 获取教师信息
	 * @return
	 */
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
	
	/**
	 * 更改教师信息
	 * @param expert
	 * @param expectStudent
	 * @return
	 */
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
	
	/**
	 * 上传教学进度表
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadResume")
	public ResponseEntity<Response> saveSchedule(
			@RequestParam("resume") MultipartFile resume) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = Long.valueOf(user.getUsername());
		
		String fileName = resume.getOriginalFilename();
		String filePath = localURL;
		SimpleDateFormat tempDate = new SimpleDateFormat("YYYYMMddHHmmss");
		String datetime = tempDate.format(new Date(System.currentTimeMillis()));
		
		try {
			FileUtil.uploadFile(resume.getBytes(), filePath,
					datetime + id + "resume" + fileName.substring(fileName.lastIndexOf(".")));
			
			Teacher teacher = teacherService.getTeacherById(id);
			teacher.setResume(fileName);
			teacher.setResumeSaveName(datetime + id + "resume" + fileName.substring(fileName.lastIndexOf(".")));
			teacherService.saveTeacher(teacher);
			
		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", fileName));

	}
	
	/**
	 * 删除教学进度表
	 * 
	 * @param file
	 * @return
	 */
	@GetMapping("/deleteResume")
	public ResponseEntity<Response> deleteSchedule() {
		try {
			
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Long id = Long.valueOf(user.getUsername());

			Teacher teacher = teacherService.getTeacherById(id);
			
			File folder = new File(localURL);
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.getName().equals(teacher.getResume())) {
					file.delete();
				}
			}
			
			teacher.setResume("0");
			teacher.setResumeSaveName("0");
			teacherService.saveTeacher(teacher);

		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功"));

	}
	
	/**
	 * 下载教学进度表
	 * 
	 * @param file
	 * @return
	 */
	@GetMapping("/downloadResume")
	public void downloadSchedule(HttpServletResponse response){
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = Long.valueOf(user.getUsername());
		
		Teacher teacher = teacherService.getTeacherById(id);
			
		String realPath = localURL+teacher.getResumeSaveName();//获取要下载的文件的绝对路径
		
		String fileName = teacher.getResume();//获取要下载的文件名
		try{
			//设置content-disposition响应头控制浏览器以下载的形式打开文件，中文文件名要使用URLEncoder.encode方法进行编码，否则会出现文件名乱码
			response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"iso-8859-1") + "\""); 
	        response.setContentType("application/octet-stream;charset=UTF-8");
			InputStream in;
			//获取文件输入流	
			in = new FileInputStream(realPath);
			int len = 0;
			byte[] buffer = new byte[1024];
			OutputStream out = response.getOutputStream();
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer,0,len);//将缓冲区的数据输出到客户端浏览器
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
