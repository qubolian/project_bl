package com.spring.boot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.blog.domain.WhatsNew;

public interface WhatsNewRepository extends JpaRepository<WhatsNew, Long>{
	Page<WhatsNew> findByEventsLikeAndDr(String events, int dr,Pageable pageable);
}
