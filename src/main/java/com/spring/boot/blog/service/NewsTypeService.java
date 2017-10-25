package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.NewsType;

public interface NewsTypeService {
	/**
	 * 保存信息类别
	 * @param NewsType
	 * @return
	 */
	NewsType saveNewsType(NewsType NewsType);
	
	/**
	 * 删除信息类别
	 * @param NewsType
	 * @return
	 */
	void removeNewsType(Long id);
	
	/**
	 * 删除列表里面的信息类别
	 * @param NewsType
	 * @return
	 */
	void removeNewsTypesInBatch(List<NewsType> NewsTypes);
	
	/**
	 * 更新信息类别
	 * @param NewsType
	 * @return
	 */
	NewsType updateNewsType(NewsType NewsType);
	
	/**
	 * 根据id获取信息类别
	 * @param NewsType
	 * @return
	 */
	NewsType getNewsTypeById(Long id);
	
	/**
	 * 获取信息类别列表
	 * @param NewsType
	 * @return
	 */
	List<NewsType> listNewsTypes();
	
	/**
	 * 根据信息类别进行分页模糊查询
	 * @param NewsType
	 * @return
	 */
	Page<NewsType> listNewsTypesByMessageTypeLike(String messageType, Pageable pageable);
}
