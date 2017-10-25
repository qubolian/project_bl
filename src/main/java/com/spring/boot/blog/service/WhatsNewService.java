package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.WhatsNew;

public interface WhatsNewService {
	/**
	 * 保存最新消息数据
	 * @param WhatsNew
	 * @return
	 */
	WhatsNew saveWhatsNew(WhatsNew whatsNew);
	
	/**
	 * 删除最新消息数据
	 * @param WhatsNew
	 * @return
	 */
	void removeWhatsNew(Long id);
	
	/**
	 * 删除列表里面的最新消息数据
	 * @param WhatsNew
	 * @return
	 */
	void removeWhatsNewsInBatch(List<WhatsNew> whatsNews);
	
	/**
	 * 更新最新消息数据
	 * @param WhatsNew
	 * @return
	 */
	WhatsNew updateWhatsNew(WhatsNew whatsNew);
	
	/**
	 * 根据id获取最新消息数据
	 * @param WhatsNew
	 * @return
	 */
	WhatsNew getWhatsNewById(Long id);
	
	/**
	 * 获取最新消息数据列表
	 * @param WhatsNew
	 * @return
	 */
	List<WhatsNew> listWhatsNews();
	
	/**
	 * 根据最新消息数据进行分页模糊查询
	 * @param WhatsNew
	 * @return
	 */
	Page<WhatsNew> listWhatsNewsByEventsLike(String events, Pageable pageable);
}
