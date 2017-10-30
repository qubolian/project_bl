package com.spring.boot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.Course;


public interface CourseRepository extends JpaRepository<Course, Long>{
	Page<Course> findByNameLike(String name,Pageable pageable);
}
