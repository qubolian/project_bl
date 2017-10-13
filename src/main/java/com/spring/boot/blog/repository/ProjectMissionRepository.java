package com.spring.boot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.ProjectMission;

/**
 * Authority 仓库.
 *
 * @since 1.0.0 2017年9月28日
 */
public interface ProjectMissionRepository extends JpaRepository<ProjectMission, Long>{
	
}
