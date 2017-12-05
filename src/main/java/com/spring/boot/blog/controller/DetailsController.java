package com.spring.boot.blog.controller;


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
import com.spring.boot.blog.domain.WhatsNew;
import com.spring.boot.blog.service.NewsTypeService;
import com.spring.boot.blog.service.ProjectMissionService;
import com.spring.boot.blog.service.WhatsNewService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/details")
public class DetailsController {
 

	@Autowired
	private WhatsNewService whatsNewService;
	
	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping("/whatsNewList")
	public ModelAndView missionShow(Model model) {
		Pageable pageable = new PageRequest(0, 10);
		Page<WhatsNew> page = whatsNewService.listWhatsNewsByEventsLike("", pageable);
		List<WhatsNew> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("whatsNewList", list);
		return new ModelAndView("details/whatsNew/list", "model", model);
	}
	
	
}
