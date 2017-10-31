package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.Teacher;

public interface TeacherService {
	/**
	 * 保存信息类别
	 * @param Teacher
	 * @return
	 */
	Teacher saveTeacher(Teacher Teacher);
	
	/**
	 * 删除信息类别
	 * @param Teacher
	 * @return
	 */
	void removeTeacher(Long id);
	
	/**
	 * 删除列表里面的信息类别
	 * @param Teacher
	 * @return
	 */
	void removeTeachersInBatch(List<Teacher> Teachers);
	
	/**
	 * 更新信息类别
	 * @param Teacher
	 * @return
	 */
	Teacher updateTeacher(Teacher Teacher);
	
	/**
	 * 根据id获取信息类别
	 * @param Teacher
	 * @return
	 */
	Teacher getTeacherById(Long id);
	
	/**
	 * 获取信息类别列表
	 * @param Teacher
	 * @return
	 */
	List<Teacher> listTeachers();
	
	/**
	 * 根据信息类别进行分页模糊查询
	 * @param Teacher
	 * @return
	 */
	Page<Teacher> listTeacherByTeacherNameLike(String teacherName, Pageable pageable);
}
