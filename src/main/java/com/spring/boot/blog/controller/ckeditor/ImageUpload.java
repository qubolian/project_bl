package com.spring.boot.blog.controller.ckeditor;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.util.FileUtil;
import com.spring.boot.blog.util.FileFormat;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/ckeditor")
public class ImageUpload {
	
	private static final String localURL = "D:/image/";
	
	
	
	/**
	 * 上传图片
	 * @param file
	 * @return
	 */

	@PostMapping("/upload")
	public void ckeditorUpload(@RequestParam("upload")MultipartFile file,
			HttpServletResponse response,
			HttpServletRequest request
			)throws Exception{
		 response.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = response.getWriter();  
		 String callback = request.getParameter("CKEditorFuncNum"); 
		 String fileName = file.getOriginalFilename();
		 String[] allowTypes = new String[] { ".jpg", ".png", "gif", ".bmp" , ".jpeg"}; 
		 FileFormat a = new FileFormat();
	     Boolean CanUploaded = a.isValid(fileName, allowTypes);  
	     if (CanUploaded) {  
	    	 String filePath = localURL;
			 SimpleDateFormat tempDate = new SimpleDateFormat("YYYYMMddHHmmss");
			 String datetime = tempDate.format(new Date(System.currentTimeMillis()));
			 String fileSaveName = datetime + java.util.UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
			 try {
					FileUtil.uploadFile(file.getBytes(), filePath,fileSaveName);
				} catch (Exception e) {
				} 
			 out.println("<script type=\"text/javascript\">");  
		     out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
		                + ",'" + request.getContextPath() + "http://127.0.0.1/" + fileSaveName + "','')");  
		     out.println("</script>");  
	     } else {  
	    	 
	     	 out.println("请上传后缀为.jpg或.png或.gif或.bmp或.jpeg的图片文件！");  
	     }  
	}
}