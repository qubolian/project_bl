package com.spring.boot.blog.controller.Super;


import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.NewsType;
import com.spring.boot.blog.domain.ProjectMission;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.service.NewsTypeService;
import com.spring.boot.blog.service.ProjectMissionService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/super")
public class NewsTypeController {
 
	@Autowired
	private NewsTypeService newsTypeService;
	
	/**
	 * 查询所有信息类别
	 * @return
	 */
	@GetMapping("/newsTypeList")
	public ModelAndView listNewsType(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="messageType",required=false,defaultValue="") String messageType,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<NewsType> page = newsTypeService.listNewsTypesByMessageTypeLike(messageType, pageable);
		List<NewsType> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("newsTypeList", list);
		return new ModelAndView(async==true?"newsType/list :: #mainContainerRepleace":"newsType/list", "newsTypeModel", model);
	}
	
	@GetMapping("/addNewsType")
	public ModelAndView createForm(Model model) {
		model.addAttribute("newsType", new NewsType(0L,null));
		return new ModelAndView("newsType/add", "newsTypeModel", model);
	}
	
	
	@PostMapping("/addNewsType")
	public ResponseEntity<Response> saveOrUpdate(NewsType newsType) {
		try {
			newsTypeService.saveNewsType(newsType);
		}catch (RuntimeException e)  {
		    return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		}catch (Exception e)  {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", newsType));
	}
	

	@GetMapping(value = "editNewsType/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		NewsType newsType= newsTypeService.getNewsTypeById(id);
		model.addAttribute("newsType", newsType);
		return new ModelAndView("newsType/edit", "newsTypeModel", model);
	}
	
	
	/**
	 * 删除信息类别
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/newsType/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			if(newsTypeService.getNewsTypeById(id)!=null){
			 newsTypeService.removeNewsType(id);
			}
		}catch (RuntimeException e) {
			Throwable cause = e.getCause();
		    if(cause instanceof org.hibernate.exception.ConstraintViolationException) {
		    	return  ResponseEntity.ok().body( new Response(false, "请先删除该信息类别下所有消息"));
		    }
		}catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	

	
	
	
	 
}
