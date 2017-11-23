package com.spring.boot.blog.controller.director;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
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


import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.CourseService;
import com.spring.boot.blog.service.UserService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.util.FileUtil;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@Controller
@RequestMapping("/director")
public class UploadController {

	
	@Value("${file.server.url}")
	private String fileServerUrl;
	
	@GetMapping("/upload")
	public ModelAndView profile(Model model) {
		model.addAttribute("fileServerUrl",fileServerUrl);
		return new ModelAndView("uploadFile/upload", "userModel", model);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<Response> saveProfile(@RequestParam("file") MultipartFile file) {
	    String fileName = file.getOriginalFilename();
	    String filePath = "D:/imgupload/";
	    try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
        }
	    return ResponseEntity.ok().body(new Response(true, "处理成功"));
	
	}
	
	 
}
