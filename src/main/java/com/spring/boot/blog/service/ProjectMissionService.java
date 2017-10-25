package com.spring.boot.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.ProjectMission;
import com.spring.boot.blog.domain.User;

/**
 * Authority 服务接口.
 * 
 * @since 1.0.0 2017年9月14日
 * @author zhangsifeng
 */
public interface ProjectMissionService {
	 
	
	/**
	 * 根据id获取 Authority
	 * @param Authority
	 * @return
	 */
	ProjectMission getProjectMissionById(Long id);
	
	ProjectMission saveProjectMission(ProjectMission projectMission);
	
	
	
}
