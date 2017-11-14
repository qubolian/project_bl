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

import com.spring.boot.blog.domain.DepartmentList;
import com.spring.boot.blog.domain.NewsType;
import com.spring.boot.blog.domain.WhatsNew;
import com.spring.boot.blog.service.DepartmentListService;
import com.spring.boot.blog.service.NewsTypeService;
import com.spring.boot.blog.service.WhatsNewService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/super")
public class WhatsNewController {
 

	
	@Autowired
	private WhatsNewService whatsNewService;
	
	@Autowired
	private DepartmentListService departmentListService;
	
	@Autowired
	private NewsTypeService newsTypeService;
	
	
	/**
	 * 查询所有最新消息
	 * @return
	 */
	@GetMapping("/whatsNewList")
	public ModelAndView listWhatsNew(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="department",required=false,defaultValue="") String events,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<WhatsNew> page = whatsNewService.listWhatsNewsByEventsLike(events, pageable);
		List<WhatsNew> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("whatsNewList", list);
		return new ModelAndView(async==true?"whatsNew/list :: #mainContainerRepleace":"whatsNew/list", "whatsNewModel", model);
	}
	
	@GetMapping("/addWhatsNew")
	public ModelAndView createForm(Model model) {
		model.addAttribute("whatsNew", new WhatsNew());
		List<DepartmentList> departmentLists = departmentListService.listDepartmentLists();
		List<NewsType> newsTypes = newsTypeService.listNewsTypes();
		
		model.addAttribute("departmentLists", departmentLists);
		model.addAttribute("newsTypes", newsTypes);
		
		
		return new ModelAndView("whatsNew/add", "whatsNewModel", model);
	}
	
	
	@PostMapping("/addWhatsNew")
	public ResponseEntity<Response> saveOrUpdate(WhatsNew whatsNew) {
		try {
			
			whatsNewService.saveWhatsNew(whatsNew);
			
		}catch (RuntimeException e)  {
			Throwable cause = e.getCause();
		    if(cause instanceof javax.persistence.RollbackException) {
		    	return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		    }
		}catch (Exception e)  {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", whatsNew));
	}
	

	@GetMapping(value = "editWhatsNew/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		WhatsNew whatsNew= whatsNewService.getWhatsNewById(id);
		
		List<DepartmentList> departmentLists = departmentListService.listDepartmentLists();
		List<NewsType> newsTypes = newsTypeService.listNewsTypes();
		model.addAttribute("departmentLists", departmentLists);
		model.addAttribute("newsTypes", newsTypes);
		
		model.addAttribute("whatsNew", whatsNew);
		
		return new ModelAndView("whatsNew/edit", "whatsNewModel", model);
	}
	
	
	/**
	 * 删除最新消息
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/whatsNew/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			if(whatsNewService.getWhatsNewById(id)!=null){
				whatsNewService.removeWhatsNew(id);
			}
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	
	 
}
