package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.spring.boot.blog.domain.CourseStandard;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.repository.CourseStandardRepository;

@Service
public class CourseStandardServiceImpl implements CourseStandardService {

	@Autowired
	CourseStandardRepository CourseStandardRepository;
	
	@Override
	@Transactional
	public CourseStandard saveCourseStandard(CourseStandard CourseStandard) {
		// TODO Auto-generated method stub
		return CourseStandardRepository.save(CourseStandard);
	}

	@Override
	@Transactional
	public void removeCourseStandard(Long id) {
		// TODO Auto-generated method stub
		CourseStandardRepository.delete(id);

	}

	@Override
	@Transactional
	public void removeCourseStandardsInBatch(List<CourseStandard> CourseStandards) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public CourseStandard updateCourseStandard(CourseStandard CourseStandard) {
		// TODO Auto-generated method stub
		return CourseStandardRepository.save(CourseStandard);
	}

	@Override
	@Transactional
	public CourseStandard getCourseStandardById(Long id) {
		// TODO Auto-generated method stub
		return CourseStandardRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<CourseStandard> listCourseStandards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<CourseStandard> listCourseStandardsByCourseId(Long id) {
		return CourseStandardRepository.findByCourseId(id);
	}
}
