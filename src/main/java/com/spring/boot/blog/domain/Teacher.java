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
	


	public Teacher(Long id,int TeacherId, String Name, String Department, String EMail, String OfficeTel,
			String Position, boolean ConditionToBeAnAdvisor, String Course, String Expert, String ExceptStudent,
			String Resume) {
		super();
		this.id = id;
		this.TeacherId = TeacherId;
		this.Name = Name;
		this.Department = Department;
		this.EMail = EMail;
		this.OfficeTel = OfficeTel;
		this.Position = Position;
		this.ConditionToBeAnAdvisor = ConditionToBeAnAdvisor;
		this.Course = Course;
		this.Expert = Expert;
		this.ExceptStudent = ExceptStudent;
		this.Resume = Resume;
	}

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
	private String Name;

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
	
	@NotEmpty(message = "授课课程不能为空")
	@Size(min=2, max=50)
	@Column(nullable = false, length = 50) // 映射为字段，值不能为空
	private String Course;
	
	
	@Size(min=2, max=1024)
	@Column(nullable = true, length = 1024) // 专长
	private String Expert;
	

	@Size(min=2, max=1024)
	@Column(nullable = true, length = 1024) // 对学生的期待
	private String ExceptStudent;
	

	@Size(min=2, max=1024)
	@Column(nullable = true, length = 1024) // 履历表
	private String Resume;

	  
	
 
}
