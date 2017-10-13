package com.spring.boot.blog.service;

import com.spring.boot.blog.domain.Authority;

/**
 * Authority 服务接口.
 * 
 * @since 1.0.0 2017年9月14日
 * @author zhangsifeng
 */
public interface AuthorityService {
	 
	
	/**
	 * 根据id获取 Authority
	 * @param Authority
	 * @return
	 */
	Authority getAuthorityById(Long id);
}
