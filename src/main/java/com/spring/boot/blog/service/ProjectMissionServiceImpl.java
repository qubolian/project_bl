package com.spring.boot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.blog.domain.ProjectMission;
import com.spring.boot.blog.repository.ProjectMissionRepository;

@Service
public class ProjectMissionServiceImpl implements ProjectMissionService {

	@Autowired
	ProjectMissionRepository ProjectMissionRepository;
	@Override
	public ProjectMission getProjectMissionById(Long id) {
		return ProjectMissionRepository.findOne(id);
	}
	
	@Override
	public ProjectMission saveProjectMission(ProjectMission projectMission) {
		// TODO Auto-generated method stub
		ProjectMission pm = ProjectMissionRepository.save(projectMission);
		return pm;
	}

	
}
