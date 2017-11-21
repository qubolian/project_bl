package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.CourseStandard;
import com.spring.boot.blog.domain.Teacher;

public interface CourseStandardService {
	/**
	 * 保存课程
	 * @param CourseStandard
	 * @return
	 */
	CourseStandard saveCourseStandard(CourseStandard CourseStandard);
	
	/**
	 * 删除课程
	 * @param CourseStandard
	 * @return
	 */
	void removeCourseStandard(Long id);
	
	/**
	 * 删除列表里面的课程
	 * @param CourseStandard
	 * @return
	 */
	void removeCourseStandardsInBatch(List<CourseStandard> CourseStandards);
	
	/**
	 * 更新课程
	 * @param CourseStandard
	 * @return
	 */
	CourseStandard updateCourseStandard(CourseStandard CourseStandard);
	
	/**
	 * 根据id获取课程
	 * @param CourseStandard
	 * @return
	 */
	CourseStandard getCourseStandardById(Long id);
	
	/**
	 * 获取课程列表
	 * @param CourseStandard
	 * @return
	 */
	List<CourseStandard> listCourseStandards();
	

	List<CourseStandard> listCourseStandardsByCourseId(Long id);
}
