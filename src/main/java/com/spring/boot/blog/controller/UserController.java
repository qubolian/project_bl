package com.spring.boot.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.blog.domain.Authority;
import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.repository.UserRepository;
import com.spring.boot.blog.service.AuthorityService;
import com.spring.boot.blog.service.UserService;
import com.spring.boot.blog.util.ConstraintViolationExceptionHandler;
import com.spring.boot.blog.vo.Response;


/**
 * 用户控制器.
 * 
 * @author <a href="https://waylau.com">Way Lau</a>
 * @date 2017年2月26日
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired 
	private UserRepository userRepository;

	@Autowired
	private AuthorityService  authorityService;
	/**
	 * 从 用户存储库 获取用户列表
	 * @return
	 */
	
	@Autowired
	private UserService userService;
	
	
	
	private Iterable<User> getUserlist() {
 		return userRepository.findAll();
	}

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping
	public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
			@RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
			@RequestParam(value="name",required=false,defaultValue="") String name,
			Model model) {
	 
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<User> page = userService.listUsersByNameLike(name, pageable);
		List<User> list = page.getContent();	// 当前所在页面数据列表
		
		model.addAttribute("page", page);
		model.addAttribute("userList", list);
		return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
	}
	
	@GetMapping("/add")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user", new User(null, 0, null, null));
		return new ModelAndView("users/add", "userModel", model);
	}

 
	/**
	 * 根据id查询用户
	 * @param message
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Long id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		model.addAttribute("title", "查看用户");
		return new ModelAndView("users/view", "userModel", model);
	}
	
	
	@GetMapping(value = "edit/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return new ModelAndView("users/edit", "userModel", model);
	}
	


	/**
	 * 新建或修改用户
	 * @param user
	 * @param result
	 * @param redirect
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response> saveOrUpdate(User user,Long[] authorityId) {
		List<Authority> authorities = new ArrayList<>();
		if(authorityId==null){
			return ResponseEntity.ok().body(new Response(false, "请先选择角色"));
		}
		for (int i = 0; i < authorityId.length; i++) {
			authorities.add(authorityService.getAuthorityById(authorityId[i]));
		}
		user.setAuthorities(authorities);
		
		if(user.getId() == 0) {
			user.setEncodePassword(user.getPassword()); // 加密密码
		}else {
			// 判断密码是否做了变更
			User originalUser = userService.getUserById(user.getId());
			String rawPassword = originalUser.getPassword();
			PasswordEncoder  encoder = new BCryptPasswordEncoder();
			String encodePasswd = encoder.encode(user.getPassword());
			boolean isMatch = encoder.matches(rawPassword, encodePasswd);
			if (!isMatch) {
				user.setEncodePassword(user.getPassword());
			}else {
				user.setPassword(user.getPassword());
			}
		}
		try {
			userService.saveUser(user);
		}  catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
		}
		return ResponseEntity.ok().body(new Response(true, "处理成功", user));
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
		try {
			userService.removeUser(id);
		} catch (Exception e) {
			return  ResponseEntity.ok().body( new Response(false, e.getMessage()));
		}
		return  ResponseEntity.ok().body( new Response(true, "处理成功"));
	}
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	

}
