package com.spring.boot.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.boot.blog.domain.Course;
import com.spring.boot.blog.domain.SubmitFile;
import com.spring.boot.blog.domain.Teacher;


public interface SubmitFileRepository extends JpaRepository<SubmitFile, Long>{
}
