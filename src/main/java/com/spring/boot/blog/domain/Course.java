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
public class Course implements Serializable {
	


	public Course(Long id, String name, String grade, String credit, String major) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.credit = credit;
		this.major = major;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "名称不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String name;

	@NotEmpty(message = "年级不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String major;

	@NotEmpty(message = "学分不能为空")
	@Size(min=1, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String credit;

	@NotEmpty(message = "专业不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String grade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	
 
}