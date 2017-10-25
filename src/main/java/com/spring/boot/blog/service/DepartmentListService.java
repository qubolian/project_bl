package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.DepartmentList;

public interface DepartmentListService {
	/**
	 * 保存发布单位
	 * @param DepartmentList
	 * @return
	 */
	DepartmentList saveDepartmentList(DepartmentList DepartmentList);
	
	/**
	 * 删除发布单位
	 * @param DepartmentList
	 * @return
	 */
	void removeDepartmentList(Long id);
	
	/**
	 * 删除列表里面的发布单位
	 * @param DepartmentList
	 * @return
	 */
	void removeDepartmentListsInBatch(List<DepartmentList> DepartmentLists);
	
	/**
	 * 更新发布单位
	 * @param DepartmentList
	 * @return
	 */
	DepartmentList updateDepartmentList(DepartmentList DepartmentList);
	
	/**
	 * 根据id获取发布单位
	 * @param DepartmentList
	 * @return
	 */
	DepartmentList getDepartmentListById(Long id);
	
	/**
	 * 获取发布单位列表
	 * @param DepartmentList
	 * @return
	 */
	List<DepartmentList> listDepartmentLists();
	
	/**
	 * 根据发布单位进行分页模糊查询
	 * @param DepartmentList
	 * @return
	 */
	Page<DepartmentList> listDepartmentListsByDepartmentLike(String department, Pageable pageable);
}
