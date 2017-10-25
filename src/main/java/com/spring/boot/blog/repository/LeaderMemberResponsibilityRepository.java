package com.spring.boot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.LeaderMemberResponsibility;

/**
 * Authority 仓库.
 *
 * @since 1.0.0 2017年9月28日
 */
public interface LeaderMemberResponsibilityRepository extends JpaRepository<LeaderMemberResponsibility, Long>{
	
}
