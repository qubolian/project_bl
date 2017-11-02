package com.spring.boot.blog.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
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
	


	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "课程名称不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String name;

	@NotEmpty(message = "学年度学期不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String term;
	
	@NotEmpty(message = "课程代码不能为空")
	@Size(min=2, max=10)
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String courseId;
	
	@NotEmpty(message = "课程序号不能为空")
	@Size(min=2, max=10)
	@Column(nullable = false, length = 10) // 映射为字段，值不能为空
	private String courseNumber;
	
	@NotEmpty(message = "考核方式不能为空")
	@Size(min=1, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String examMethods;
	
	@NotEmpty(message = "学分不能为空")
	@Size(min=1, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String credit;

	@NotEmpty(message = "课时不能为空")
	@Size(min=1, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String period;
	
	@NotEmpty(message = "班级不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String classes;

	@NotEmpty(message = "课程类别不能为空")
	@Size(min=2, max=32)
	@Column(nullable = false, length = 32) // 映射为字段，值不能为空
	private String courseType;
	
	@NotEmpty(message = "课程状态默认为未发布")
	@Size(min=1, max=5)
	@Column(nullable = false, length = 5) // 映射为字段，值不能为空
	private String status;
	

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getExamMethods() {
		return examMethods;
	}

	public void setExamMethods(String examMethods) {
		this.examMethods = examMethods;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}




	
	
 
}
