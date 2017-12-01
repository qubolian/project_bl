package com.spring.boot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.DepartmentList;

public interface DepartmentListRepository extends JpaRepository<DepartmentList, Long> {
	Page<DepartmentList> findByDepartmentLikeAndDr(String department, int dr, Pageable pageable);
}
