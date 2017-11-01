package com.spring.boot.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.blog.domain.Student;

public interface StudentService {
	/**
	 * 保存学生
	 * @param Teacher
	 * @return
	 */
	Student saveStudent(Student Student);
	
	/**
	 * 删除学生
	 * @param Teacher
	 * @return
	 */
	void removeStudent(Long id);
	
	/**
	 * 删除列表里面的学生
	 * @param Teacher
	 * @return
	 */
	void removeStudentsInBatch(List<Student> Students);
	
	/**
	 * 更新学生
	 * @param Teacher
	 * @return
	 */
	Student updateStudent(Student Student);
	
	/**
	 * 根据id获取学生
	 * @param Teacher
	 * @return
	 */
	Student getStudentById(Long id);
	
	/**
	 * 获取学生列表
	 * @param Teacher
	 * @return
	 */
	List<Student> listStudents();
	
	/**
	 * 根据学生进行分页模糊查询
	 * @param Teacher
	 * @return
	 */
	Page<Student> listStudentByNameLike(String Name, Pageable pageable);
}
