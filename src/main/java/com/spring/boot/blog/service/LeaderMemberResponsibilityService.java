package com.spring.boot.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.LeaderMemberResponsibility;
import com.spring.boot.blog.domain.User;

/**
 * Authority 服务接口.
 * 
 * @since 1.0.0 2017年9月14日
 * @author zhangsifeng
 */
public interface LeaderMemberResponsibilityService {
	 
	
	/**
	 * 根据id获取 Authority
	 * @param Authority
	 * @return
	 */
	LeaderMemberResponsibility getLeaderMemberResponsibilityById(Long id);
	
	LeaderMemberResponsibility saveLeaderMemberResponsibility(LeaderMemberResponsibility leaderMemberResponsibility);
	
	
	
}
