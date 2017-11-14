package com.spring.boot.blog.controller.ckeditor;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/ckeditor")
public class ImageUpload {
	/**
	 * 上传图片
	 * @param file
	 * @return
	 */

	@PostMapping("/upload")
	public String ckeditorUpload(@RequestParam("upload")MultipartFile file,String CKEditorFuncNum)throws Exception{
	    // 获取文件名
	    String fileName = file.getOriginalFilename();
	    // 获取文件的后缀名
	    String suffixName = fileName.substring(fileName.lastIndexOf("."));
	    
	    
/*	    String newFileName=DateUtil.getCurrentDateStr()+suffixName;
	    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));
	     
	    StringBuffer sb=new StringBuffer();
	    sb.append("<script type=\"text/javascript\">");
	    sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/static/filmImage/"+ newFileName + "','')");
	    sb.append("</script>");*/
	     System.out.println(fileName+suffixName);
	    return null;
	}
}