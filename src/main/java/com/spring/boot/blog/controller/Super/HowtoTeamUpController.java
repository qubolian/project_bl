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

import com.spring.boot.blog.domain.HowtoTeamUp;
import com.spring.boot.blog.domain.NewsType;
import com.spring.boot.blog.service.HowtoTeamUpService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;

/**
 * 后台页面控制器.
 * 
 * @date 2017年9月26日
 */
@RestController
@RequestMapping("/super")
public class HowtoTeamUpController {
 

	
	@Autowired
	private HowtoTeamUpService howtoTeamUpService;
	
	/**
	 * 查询所有信息类别
	 * @return
	 */
	@GetMapping("/howtoTeamUpList")
	public ModelAndView listhowtoTeamUp(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="gradeId",required=false,defaultValue="") String gradeId,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<HowtoTeamUp> page = howtoTeamUpService.listHowtoTeamUpByGradeIdLike(gradeId, pageable);
		List<HowtoTeamUp> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("howtoTeamUpList", list);
		return new ModelAndView(async==true?"howtoTeamUp/list :: #mainContainerRepleace":"howtoTeamUp/list", "howtoTeamUpModel", model);
	}
	
	@GetMapping("/addHowtoTeamUp")
	public ModelAndView createForm(Model model) {
		model.addAttribute("howtoTeamUp", new HowtoTeamUp(0L,null,null,null,null));
		return new ModelAndView("howtoTeamUp/add", "howtoTeamUpModel", model);
	}
	
	
	@PostMapping("/addHowtoTeamUp")
	public ResponseEntity<Response> saveOrUpdate(HowtoTeamUp howtoTeamUp) {
		try {
			howtoTeamUpService.saveHowtoTeamUp(howtoTeamUp);
		}catch (RuntimeException e)  {
			Throwable cause = e.getCause();
		    if(cause instanceof javax.persistence.RollbackException) {
		    	return ResponseEntity.ok().body(new Response(false, "更改值有误，请重试！"));
		    }
		}catch (Exception e)  {
			return ResponseEntity.ok().body(new Response(false, e.getMessage()));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", howtoTeamUp));
	}
	

	@GetMapping(value = "editHowtoTeamUp/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		HowtoTeamUp howtoTeamUp= howtoTeamUpService.getHowtoTeamUpById(id);
		model.addAttribute("howtoTeamUp", howtoTeamUp);
		return new ModelAndView("howtoTeamUp/edit", "howtoTeamUpModel", model);
	}
	
	
	/**
	 * 删除信息类别
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/howtoTeamUp/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			if(howtoTeamUpService.getHowtoTeamUpById(id)!=null){
				howtoTeamUpService.removeHowtoTeamUp(id);
			}
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	
	 
}
