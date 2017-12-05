package com.spring.boot.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.DepartmentList;
import com.spring.boot.blog.domain.Teacher;


public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	
	Page<Teacher> findByTeacherNameLikeOrderByStatusIntAsc(String TeacherName,Pageable pageable);
	
	List<Teacher> findByDepartment(DepartmentList Department);

}
