package com.spring.boot.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.Teacher;
import com.spring.boot.blog.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository CourseRepository;
	
	@Override
	@Transactional
	public Course saveCourse(Course Course) {
		// TODO Auto-generated method stub
		return CourseRepository.save(Course);
	}

	@Override
	@Transactional
	public void removeCourse(Long id) {
		// TODO Auto-generated method stub
		CourseRepository.delete(id);

	}

	@Override
	@Transactional
	public void removeCoursesInBatch(List<Course> Courses) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Course updateCourse(Course Course) {
		// TODO Auto-generated method stub
		return CourseRepository.save(Course);
	}

	@Override
	@Transactional
	public Course getCourseById(Long id) {
		// TODO Auto-generated method stub
		return CourseRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<Course> listCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Page<Course> listCoursesByNameLike(String name, String status, Pageable pageable) {
		// TODO Auto-generated method stub
		name= "%"+name+"%";
		Page<Course> Courses = CourseRepository.findByNameLikeAndStatus(name,status, pageable);
		return Courses;
	}
	
	@Override
	@Transactional
	public Page<Course> listCoursesByTeacherIdAndStatus(Long id, String status,Pageable pageable){
		Page<Course> Courses = CourseRepository.findByTeacherIdAndStatus(id,status, pageable);
		return Courses;
	}
	
}
