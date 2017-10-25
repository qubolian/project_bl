package com.spring.boot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.HowtoTeamUp;

public interface HowtoTeamUpRepository extends JpaRepository<HowtoTeamUp, Long>{
	Page<HowtoTeamUp> findByGradeIdLike(String GradeId,Pageable pageable);
}
