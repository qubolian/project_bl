package com.spring.boot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.NewsType;
import com.spring.boot.blog.domain.User;

public interface NewsTypeRepository extends JpaRepository<NewsType, Long>{
	Page<NewsType> findByMessageTypeLike(String messageType,Pageable pageable);
}
