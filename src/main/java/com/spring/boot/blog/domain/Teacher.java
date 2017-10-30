package com.spring.boot.blog.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 发布单位
 * 
 * @since 1.0.0 2017年4月7日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity // 实体
public class Teacher implements Serializable {
	


	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识
	
	@NotEmpty(message = "教师编号不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private int TeacherId;

	@NotEmpty(message = "教师名称不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String TeacherName;
	

	@NotEmpty(message = "科系不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String Department;

	
	@Size(min=1, max=32)
	@Column(nullable = true, length = 32) // E-Mail
	private String EMail;

	@NotEmpty(message = "办公室电话不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String OfficeTel;

	
	@Size(min=2, max=32)
	@Column(nullable = true, length = 32) // 职称
	private String Position;
	
	@NotEmpty(message = "是否可以担任指导老师不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private boolean ConditionToBeAnAdvisor;
	
	@Size(min=2, max=1024)
	@Column(nullable = true, length = 1024) // 专长
	private String Expert;
	

	@Size(min=2, max=1024)
	@Column(nullable = true, length = 1024) // 对学生的期待
	private String ExceptStudent;
	

	@Size(min=2, max=1024)
	@Column(nullable = true, length = 1024) // 履历表
	private String Resume;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getTeacherId() {
		return TeacherId;
	}


	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}


	public String getTeacherName() {
		return TeacherName;
	}


	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}


	public String getDepartment() {
		return Department;
	}


	public void setDepartment(String department) {
		Department = department;
	}


	public String getEMail() {
		return EMail;
	}


	public void setEMail(String eMail) {
		EMail = eMail;
	}


	public String getOfficeTel() {
		return OfficeTel;
	}


	public void setOfficeTel(String officeTel) {
		OfficeTel = officeTel;
	}


	public String getPosition() {
		return Position;
	}


	public void setPosition(String position) {
		Position = position;
	}


	public boolean isConditionToBeAnAdvisor() {
		return ConditionToBeAnAdvisor;
	}


	public void setConditionToBeAnAdvisor(boolean conditionToBeAnAdvisor) {
		ConditionToBeAnAdvisor = conditionToBeAnAdvisor;
	}


	public String getExpert() {
		return Expert;
	}


	public void setExpert(String expert) {
		Expert = expert;
	}


	public String getExceptStudent() {
		return ExceptStudent;
	}


	public void setExceptStudent(String exceptStudent) {
		ExceptStudent = exceptStudent;
	}


	public String getResume() {
		return Resume;
	}


	public void setResume(String resume) {
		Resume = resume;
	}


	  
	
 
}
