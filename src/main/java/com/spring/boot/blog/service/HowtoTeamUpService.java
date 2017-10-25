package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.HowtoTeamUp;

public interface HowtoTeamUpService {
	/**
	 * 保存组队方法
	 * @param HowtoTeamUp
	 * @return
	 */
	HowtoTeamUp saveHowtoTeamUp(HowtoTeamUp howtoTeamUp);
	
	/**
	 * 删除组队方法
	 * @param HowtoTeamUp
	 * @return
	 */
	void removeHowtoTeamUp(Long id);
	
	/**
	 * 删除列表里面的组队方法
	 * @param HowtoTeamUp
	 * @return
	 */
	void removeHowtoTeamUpInBatch(List<HowtoTeamUp> howtoTeamUp);
	
	/**
	 * 更新组队方法
	 * @param HowtoTeamUp
	 * @return
	 */
	HowtoTeamUp updateHowtoTeamUp(HowtoTeamUp howtoTeamUp);
	
	/**
	 * 根据id获取组队方法
	 * @param HowtoTeamUp
	 * @return
	 */
	HowtoTeamUp getHowtoTeamUpById(Long id);
	
	/**
	 * 获取组队方法列表
	 * @param HowtoTeamUp
	 * @return
	 */
	List<HowtoTeamUp> listHowtoTeamUp();
	
	/**
	 * 根据组队方法进行分页模糊查询
	 * @param HowtoTeamUp
	 * @return
	 */
	Page<HowtoTeamUp> listHowtoTeamUpByGradeIdLike(String gradeId, Pageable pageable);
}
