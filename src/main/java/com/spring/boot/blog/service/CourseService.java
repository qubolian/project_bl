package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.Course;

public interface CourseService {
	/**
	 * 保存课程
	 * @param Course
	 * @return
	 */
	Course saveCourse(Course Course);
	
	/**
	 * 删除课程
	 * @param Course
	 * @return
	 */
	void removeCourse(Long id);
	
	/**
	 * 删除列表里面的课程
	 * @param Course
	 * @return
	 */
	void removeCoursesInBatch(List<Course> Courses);
	
	/**
	 * 更新课程
	 * @param Course
	 * @return
	 */
	Course updateCourse(Course Course);
	
	/**
	 * 根据id获取课程
	 * @param Course
	 * @return
	 */
	Course getCourseById(Long id);
	
	/**
	 * 获取课程列表
	 * @param Course
	 * @return
	 */
	List<Course> listCourses();
	
	/**
	 * 根据课程进行分页模糊查询
	 * @param Course
	 * @return
	 */
	Page<Course> listCoursesByNameLike(String name, Pageable pageable);
	
	
}
