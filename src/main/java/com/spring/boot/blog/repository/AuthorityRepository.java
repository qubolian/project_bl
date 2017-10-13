package com.spring.boot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.Authority;

/**
 * Authority 仓库.
 *
 * @since 1.0.0 2017年9月14日
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
}
