package com.spring.boot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	Page<Student> findByNameLike(String Student,Pageable pageable);
}
