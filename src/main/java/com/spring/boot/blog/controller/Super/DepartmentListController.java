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
import com.spring.boot.blog.service.DepartmentListService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/super")
public class DepartmentListController {
 

	
	@Autowired
	private DepartmentListService departmentListService;
	
	/**
	 * 查询所有信息类别
	 * @return
	 */
	@GetMapping("/departmentListList")
	public ModelAndView listNewsType(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="department",required=false,defaultValue="") String department,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<DepartmentList> page = departmentListService.listDepartmentListsByDepartmentLike(department, pageable);
		List<DepartmentList> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("departmentListList", list);
		return new ModelAndView(async==true?"departmentList/list :: #mainContainerRepleace":"departmentList/list", "departmentListModel", model);
	}
	
	@GetMapping("/addDepartmentList")
	public ModelAndView createForm(Model model) {
		model.addAttribute("departmentList", new DepartmentList(0L,null,null));
		return new ModelAndView("departmentList/add", "departmentListModel", model);
	}
	
	
	@PostMapping("/addDepartmentList")
	public ResponseEntity<Response> saveOrUpdate(DepartmentList departmentList) {
		try {
			departmentListService.saveDepartmentList(departmentList);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", departmentList));
	}
	

	@GetMapping(value = "editDepartmentList/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		DepartmentList departmentList= departmentListService.getDepartmentListById(id);
		model.addAttribute("departmentList", departmentList);
		return new ModelAndView("departmentList/edit", "departmentListModel", model);
	}
	
	
	/**
	 * 删除信息类别
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/departmentList/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			if(departmentListService.getDepartmentListById(id)!=null){
				departmentListService.removeDepartmentList(id);
			}
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	
	 
}
