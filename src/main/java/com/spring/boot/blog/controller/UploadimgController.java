package com.spring.boot.blog.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.UserService;
import com.spring.boot.blog.util.FileUtil;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/uploadimg")
public class UploadimgController {

	@Autowired
	private UserService userService;

	@Autowired
	private static final String localURL = "/usr/local/image/";

	/**
	 * 上传头像
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	public ResponseEntity<Response> uploadimg(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String filePath = localURL;
		SimpleDateFormat tempDate = new SimpleDateFormat("YYYYMMddHHmmss");
		String datetime = tempDate.format(new Date(System.currentTimeMillis()));
		try {
			FileUtil.uploadFile(file.getBytes(), filePath, datetime + fileName.substring(fileName.lastIndexOf(".")));

		} catch (Exception e) {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok()
				.body(new Response(true, "处理成功", datetime + fileName.substring(fileName.lastIndexOf("."))));

	}

	/**
	 * 保存头像
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/saveAvatar")
	public ResponseEntity<Response> saveSchedule(
			@RequestParam(value = "avatar", required = false, defaultValue = "") String avatar) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		File folder = new File(localURL);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().equals(user.getAvatar())) {
				file.delete();
			}
		}

		user.setAvatar(avatar);
		userService.saveUser(user);

		return ResponseEntity.ok().body(new Response(true, "处理成功"));

	}

}
